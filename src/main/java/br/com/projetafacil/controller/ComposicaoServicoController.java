package br.com.projetafacil.controller;

import br.com.projetafacil.controller.validator.ComposicaoServicoValidator;
import br.com.projetafacil.dto.InsumoDTO;
import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.model.ItemComposicaoInsumo;
import br.com.projetafacil.model.enums.EnumReferencia;
import br.com.projetafacil.model.enums.EnumUnidade;
import br.com.projetafacil.model.enums.TipoInsumo;
import br.com.projetafacil.page.PageWrapper;
import br.com.projetafacil.repository.Classes;
import br.com.projetafacil.repository.Composicoes;
import br.com.projetafacil.repository.Insumos;
import br.com.projetafacil.repository.filter.ComposicaoServicoFilter;
import br.com.projetafacil.security.UsuarioSistema;
import br.com.projetafacil.service.ComposicaoServicoService;
import br.com.projetafacil.session.TabelasItensComposicaoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/composicoes")
public class ComposicaoServicoController {

	private static String COMPOSICAO_SERVICO_VIEW = "composicao/CadastroComposicaoServico";
	private static String COMPOSICAO_SERVICO_LIST_VIEW = "composicao/PesquisaComposicaoServico";
	private static String REDIRECT_SERVICO_VIEW = "redirect:/composicoes/nova";
	
	
	@Autowired
	private Insumos insumos;
	
	
	@Autowired
	private Classes classes;
	
	@Autowired 
	private ComposicaoServicoService composicaoServicoService;
	
	@Autowired
	private Composicoes composicoes;
	
	
	@Autowired
	TabelasItensComposicaoSession tabelasItensComposicaoSession;
	
	@Autowired
	ComposicaoServicoValidator composicaoServicoValidator;

	@InitBinder("composicaoServico")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(composicaoServicoValidator);
	}
	/**
	 * Nova Composição de Serviço
	 * @return
	 */
	@RequestMapping("/nova")
	public ModelAndView nova(ComposicaoServico composicaoServico) {
		ModelAndView mv = new ModelAndView(COMPOSICAO_SERVICO_VIEW);
	
		setUuid(composicaoServico);
		
		mv.addObject("referencias", EnumReferencia.values());
		mv.addObject("referenciaPropia", EnumReferencia.PROPRIA);
		mv.addObject("unidades", EnumUnidade.values());
		mv.addObject("classes", classes.findAll());
		mv.addObject("itemInsumo", composicaoServico.getInsumos());
		mv.addObject("itemComposicao", composicaoServico.getInsumosComposicao());
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar(ComposicaoServicoFilter composicaoServicoFilter, @PageableDefault(size = 10) Pageable pageable, 
												HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		ModelAndView mv = new ModelAndView(COMPOSICAO_SERVICO_LIST_VIEW);
		composicaoServicoFilter.setUsuario(usuarioSistema.getUsuario());
		PageWrapper<ComposicaoServico> paginaWrapper = new PageWrapper<>(composicoes.filtrar(composicaoServicoFilter, pageable)
																									, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			composicaoServicoService.excluir(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/nova")
	public ModelAndView salvar(ComposicaoServico composicaoServico, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarComposicaoServico(composicaoServico, result);
		if (result.hasErrors()) {
			return nova(composicaoServico);
		}
		
		composicaoServico.setUsuario(usuarioSistema.getUsuario());
		
		composicaoServicoService.salvar(composicaoServico);
		attributes.addFlashAttribute("mensagem", "Composição de Serviço salva com sucesso");
		return new ModelAndView(REDIRECT_SERVICO_VIEW);
	}
	
	private void validarComposicaoServico(ComposicaoServico composicaoServico, BindingResult result) {
		
		composicaoServico.adicionarItens(tabelasItensComposicaoSession.getItensInsumo(composicaoServico.getUuid()));
		
		composicaoServicoValidator.validate(composicaoServico, result);
	}
	
	@GetMapping("/visualizar/{id}")
	public ModelAndView visualizar(@PathVariable Long id) {
		return editar(id);
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ComposicaoServico composicaoServico = composicoes.buscarComItens(id);

		setUuid(composicaoServico);

		for (ItemComposicaoInsumo item : composicaoServico.getInsumos()) {
			if(item.getTipoInsumo() == TipoInsumo.COMPOSICAO) {
				tabelasItensComposicaoSession.adicionarItemComposicao(composicaoServico.getUuid(), item.getComposicaoDaComposicao(),
						item.getValorCoeficiente());
			} else {
				tabelasItensComposicaoSession.adicionarItem(composicaoServico.getUuid(), item.getInsumo(),
						item.getValorCoeficiente());
			}

		}

		
//		for (ItemComposicaoComposicao item : composicaoServico.getComposicoes()) {
//			tabelasItensComposicaoSession.adicionarItemComposicao(composicaoServico.getUuid(),
//					item.getComposicaoDeComposicao(), item.getValorCoeficiente());
//		}

		ModelAndView mv = nova(composicaoServico);
		mv.addObject(composicaoServico);
		return mv;
	}
	
	
	

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<InsumoDTO> pesquisarInsumo(String nomeOuCodigoBim, String tipoInsumo ) {
		return composicoes.buscarInsumoPorNomeOuCodigoBIM(nomeOuCodigoBim, tipoInsumo);
	}
	
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long idInsumo, String uuid, String tipoInsumo) {
		
		switch(tipoInsumo) {
			case "Insumo" :
				Insumo insumo = insumos.findOne(idInsumo);
				tabelasItensComposicaoSession.adicionarItem(uuid, insumo, BigDecimal.ONE);
				return mvTabelaItensComposicao(uuid);
			case "ComposicaoServico" :
				ComposicaoServico composicaoServico = composicoes.findOne(idInsumo);
				tabelasItensComposicaoSession.adicionarItemComposicao(uuid, composicaoServico, BigDecimal.ONE);
				return mvTabelaItensComposicao(uuid);
		}
		
		return mvTabelaItensComposicao(uuid);
		
	}
	
	
	
	@PutMapping("/item/{idInsumo}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("idInsumo") Long id, BigDecimal coeficiente, String tipoInsumo, String uuid) {
		System.out.println(tipoInsumo);
		switch(tipoInsumo) {
		case "Insumo" :
			Insumo insumo = insumos.findOne(id);
			tabelasItensComposicaoSession.alterarCoeficienteItemMaterial(uuid, insumo, coeficiente);
			return mvTabelaItensComposicao(uuid);
			
		case "ComposicaoServico" :
			ComposicaoServico composicaoServico = composicoes.findOne(id);
			tabelasItensComposicaoSession.alterarCoeficienteItemComposicao(uuid, composicaoServico, coeficiente);
			return mvTabelaItensComposicao(uuid);
		}
		
		return mvTabelaItensComposicao(uuid);	
	}
		
		
	
	
	@DeleteMapping("/excluirInsumo/{uuid}/{tipoInsumo}/{id}")
	public ModelAndView excluirItemInsumo(@PathVariable("id") Long id,
						@PathVariable("tipoInsumo") String tipoInsumo, @PathVariable("uuid") String uuid ) {

		switch(tipoInsumo) {
			case "Insumo" :
				Insumo insumo = insumos.findOne(id);
				tabelasItensComposicaoSession.excluirItem(uuid, insumo);
				return mvTabelaItensComposicao(uuid);
				
			case "ComposicaoServico" :
				ComposicaoServico composicaoServico = composicoes.findOne(id);
				tabelasItensComposicaoSession.excluirItemComposicao(uuid, composicaoServico);
				return mvTabelaItensComposicao(uuid);
	}
	
		return mvTabelaItensComposicao(uuid);
	}
	
	

	private ModelAndView mvTabelaItensComposicao(String uuid) {
		ModelAndView mv = new ModelAndView("composicao/TabelaItensComposicao");
		mv.addObject("itemInsumo", tabelasItensComposicaoSession.getItensInsumo(uuid));
		mv.addObject("itemComposicao", tabelasItensComposicaoSession.getItensInsumoComposicao(uuid));
		return mv;
	}
	
	
	
	private void setUuid(ComposicaoServico composicaoServico) {
		if(StringUtils.isEmpty(composicaoServico.getUuid())) {
			composicaoServico.setUuid(UUID.randomUUID().toString());
		}
		
	}

	
}