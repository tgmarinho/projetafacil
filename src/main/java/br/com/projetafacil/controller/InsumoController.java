package br.com.projetafacil.controller;

import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.model.enums.EnumUnidade;
import br.com.projetafacil.model.enums.TipoInsumo;
import br.com.projetafacil.page.PageWrapper;
import br.com.projetafacil.repository.Classes;
import br.com.projetafacil.repository.Insumos;
import br.com.projetafacil.repository.filter.InsumoFilter;
import br.com.projetafacil.service.InsumosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/insumos")
public class InsumoController {

	private static String INSUMO_VIEW = "insumo/CadastroInsumo";
	private static String INSUMO_PESQUISA = "insumo/PesquisaInsumo";
	private static final String REDIRECT_NOVO_INSUMO = "redirect:/insumos/novo";
	
	@Autowired
	private Insumos insumos;
	
	@Autowired
	private InsumosService insumosService;
	
	
	@RequestMapping("/novo")
	public ModelAndView novo(Insumo insumo) {
		ModelAndView mv = new ModelAndView(INSUMO_VIEW);
		mv.addObject("unidades", EnumUnidade.values());
		mv.addObject("tipos", TipoInsumo.values());
		mv.addObject(insumo);
		return mv;
	}
	
	
	
	@GetMapping
	public ModelAndView pesquisar(InsumoFilter insumoFilter, BindingResult result, @PageableDefault(size = 30) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(INSUMO_PESQUISA);
		mv.addObject("tipos", TipoInsumo.values());
		PageWrapper<Insumo> paginaWrapper = new PageWrapper<>(insumos.filtrar(insumoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	
	
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Insumo insumo, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors() ) {
			return novo(insumo);
		}

		try {
			insumosService.salvar(insumo);
			attributes.addFlashAttribute("mensagem", "Insumo salvo com sucesso!");
			return new ModelAndView(REDIRECT_NOVO_INSUMO);
		} catch (IllegalArgumentException e) {
			// errors.rejectValue("mensagemErro", null, e.getMessage());
			attributes.addFlashAttribute("mensagemErro", "Problema ao salvar o insumo!" + e.getMessage());
			return novo(insumo);
		}
	}
	
	@GetMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		return novo(insumos.findOne(id));
	}
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			insumosService.excluir(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	
}
