package br.com.projetafacil.controller;

import br.com.projetafacil.controller.validator.OrcamentoValidator;
import br.com.projetafacil.dto.ComposicaoServicoDTO;
import br.com.projetafacil.model.*;
import br.com.projetafacil.page.PageWrapper;
import br.com.projetafacil.repository.*;
import br.com.projetafacil.repository.filter.OrcamentoFilter;
import br.com.projetafacil.security.UsuarioSistema;
import br.com.projetafacil.service.OrcamentoService;
import br.com.projetafacil.service.material.DetalheMaterialService;
import br.com.projetafacil.session.TabelasItensOrcamentoSession;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping("/orcamentos")
public class OrcamentoController {
	
	private static String ORCAMENTO_VIEW = "orcamento/CadastroOrcamento";
	private static String ORCAMENTO_LIST_VIEW = "orcamento/PesquisaOrcamento";
	private static String REDIRECT_ORCAMENTO_VIEW = "redirect:/orcamentos/novo";
	private static String TABELA_ITENS_ORCAMENTO = "orcamento/TabelaItensOrcamentoEtapa";
	private static String DETALHES_MATERIAL = "orcamento/DetalhesMateriaisOrcamento";
	private static String DETALHES_MAO_DE_OBRA = "orcamento/DetalhesMaoDeObraPropriaOrcamento";
	private static String PRECOS_DOS_FORNECEDORES = "orcamento/PrecosDosFornecedores";
	
	@Autowired
	private OrcamentoService orcamentoService;
	
	@Autowired
	private Classes classes;
	
	
	@Autowired
	private Orcamentos orcamentos;
	
	@Autowired
	private Composicoes composicoes;
	
	
	@Autowired
	private Cidades cidades;
	
	@Autowired
	private br.com.projetafacil.repository.Estados estados;
	
	@Autowired
	TabelasItensOrcamentoSession tabelasItensOrcamentoSession;
	
	@Autowired
	OrcamentoValidator orcamentoValidator;
	
	@Autowired
	private Fornecedores fornecedores;
	
	
	@InitBinder("orcamento")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(orcamentoValidator);
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Orcamento orcamento) {
		ModelAndView mv = new ModelAndView(ORCAMENTO_VIEW);
		
		setUuid(orcamento);
		mv.addObject("itensOrcamento", orcamento.getItensOrcamento());
		mv.addObject("valorBDI", orcamento.getValorBDI());
		mv.addObject("valorTotalItens", orcamento.getValorTotalItens());
		mv.addObject("estados", estados.findAll());
		mv.addObject("classes", classes.findAll());

		mv.addObject("fornecedores", fornecedores.findAll());
		
		
		return mv;
	}

	@GetMapping
	public ModelAndView pesquisar(OrcamentoFilter orcamentoFilter, @PageableDefault(size = 10) Pageable pageable, 
																HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		ModelAndView mv = new ModelAndView(ORCAMENTO_LIST_VIEW);
		orcamentoFilter.setUsuario(usuarioSistema.getUsuario());
		PageWrapper<Orcamento> paginaWrapper = new PageWrapper<>(orcamentos.filtrar(orcamentoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			orcamentoService.excluir(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/novo")
	public ModelAndView salvar(Orcamento orcamento, BindingResult result, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		validarOrcamento(orcamento, result);
		if (result.hasErrors()) {
			return novo(orcamento);
		}
		orcamento.setUsuario(usuarioSistema.getUsuario());
		orcamentoService.salvar(orcamento);
		attributes.addFlashAttribute("mensagem", "Orçamento salvo com sucesso");
		return new ModelAndView(REDIRECT_ORCAMENTO_VIEW);
	}
	
	private void validarOrcamento(Orcamento orcamento, BindingResult result) {
		Set<ItemOrcamento> itensSet = new HashSet<ItemOrcamento>();
		List<ItemOrcamento> itensList = tabelasItensOrcamentoSession.getItensOrcamento(orcamento.getUuid());
		for (ItemOrcamento item : itensList) {
			itensSet.add(item);
		}
		orcamento.adicionarItens(itensSet);
		orcamento.calcularValorTotal();
		orcamentoValidator.validate(orcamento, result);
	}
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Orcamento orcamento = orcamentos.buscarComItens(id);
		setUuid(orcamento);
		for (ItemOrcamento item : orcamento.getItensOrcamento()) {
			tabelasItensOrcamentoSession.adicionarItem(orcamento.getUuid(), item.getComposicaoServico(), item.getQuantidade());
		}
		ModelAndView mv = novo(orcamento);
		mv.addObject(orcamento);
		mv.addObject("materiaisDetalhados", orcamentoService.detalharMaterial(tabelasItensOrcamentoSession.getItensOrcamento(orcamento.getUuid()), orcamento.getCidade().getEstado()));
		mv.addObject("maosDeObraDetalhados", tabelasItensOrcamentoSession.detalharMaoDeObra(orcamento.getUuid()));
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ComposicaoServicoDTO> pesquisar(String nomeOuCodigoBim, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		return composicoes.buscarServicoPorNomeOuCodigoBIM(nomeOuCodigoBim, usuarioSistema.getUsuario());
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long idComposicaoServico, String uuid) {
		ComposicaoServico composicaoServico = composicoes.buscarComItens(idComposicaoServico);
		tabelasItensOrcamentoSession.adicionarItem(uuid, composicaoServico, BigDecimal.ONE);
		return mvTabelaItensOrcamento(uuid);
	}
	
	@PutMapping("/item/{idServico}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("idServico") Long id, BigDecimal quantidade, String uuid) {
		ComposicaoServico composicaoServico = composicoes.buscarComItens(id);
		tabelasItensOrcamentoSession.alterarQuantidadeItem(uuid, composicaoServico, quantidade);
		return mvTabelaItensOrcamento(uuid);	
	}

	@DeleteMapping("/excluirServico/{uuid}/{id}")
	public ModelAndView excluirServico(@PathVariable("id") Long id, @PathVariable("uuid") String uuid ) {
		ComposicaoServico composicaoServico = composicoes.findOne(id);
		tabelasItensOrcamentoSession.excluirItem(uuid, composicaoServico);
		return mvTabelaItensOrcamento(uuid);
	}
	
	@PostMapping("/orcar")
	public ModelAndView orcar(String uuid) {
		return mvTabelaItensOrcamento(uuid);
	}
	
	private ModelAndView mvTabelaItensOrcamento(String uuid) {
		ModelAndView mv = new ModelAndView(TABELA_ITENS_ORCAMENTO);
		mv.addObject("itensOrcamento", tabelasItensOrcamentoSession.getItensOrcamento(uuid));
		mv.addObject("valorTotal", tabelasItensOrcamentoSession.getValorTotal(uuid));
		mv.addObject("valorTotalItens", tabelasItensOrcamentoSession.getValorTotal(uuid));
		mv.addObject("classes", classes.findAll());
//		mv.addObject("materiaisDetalhados", tabelasItensOrcamentoSession.detalharMaterial(uuid));
//		mv.addObject("maosDeObraDetalhados", tabelasItensOrcamentoSession.detalharMaoDeObra(uuid));
		return mv;
	}
	
	private void setUuid(Orcamento orcamento) {
		if(StringUtils.isEmpty(orcamento.getUuid())) {
			orcamento.setUuid(UUID.randomUUID().toString());
		}
	}
	
	@GetMapping("/servicos/{uuid}")
	public ModelAndView detalharServicos(@PathVariable("uuid") String uuid) {
		return mvTabelaItensOrcamento(uuid);
	}
	
	@GetMapping("/detalheMaterial/{uuid}")
	public ModelAndView detalharMaterial(@PathVariable("uuid") String uuid, Estado estado) {
		ModelAndView mv = new ModelAndView(DETALHES_MATERIAL);
		
		mv.addObject("valorTotal", tabelasItensOrcamentoSession.getValorTotal(uuid));
		mv.addObject("materiaisDetalhados", new DetalheMaterialService().
						listarMateriaisDoOrcamento(tabelasItensOrcamentoSession.getItensOrcamento(uuid), estado));
		return mv; 
	}
	
	@GetMapping("/detalheMaoDeObraPropria/{uuid}")
	public ModelAndView detalharMaoDeObra(@PathVariable("uuid") String uuid) {
		ModelAndView mv = new ModelAndView(DETALHES_MAO_DE_OBRA);
		mv.addObject("valorTotal", tabelasItensOrcamentoSession.getValorTotal(uuid));
		mv.addObject("maosDeObraDetalhados", tabelasItensOrcamentoSession.detalharMaoDeObra(uuid));
		return mv;
	}
	
	@GetMapping("/precoDosFornecedores/{uuid}")
	public ModelAndView precosDosFornecedores(@PathVariable("uuid") String uuid) {
		ModelAndView mv = new ModelAndView(PRECOS_DOS_FORNECEDORES);
		mv.addObject("fornecedores", fornecedores.findAll());
		
		return mv;
	}
	
	
	 
	
	@RequestMapping(value="/cidades", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Cidade> pesquisarPorIdEstado(
			@RequestParam(name = "estado", defaultValue = "-1") Long idEstado) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {	}
		return cidades.findByEstadoId(idEstado);
	}
}
