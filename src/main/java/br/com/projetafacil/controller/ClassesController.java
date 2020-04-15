package br.com.projetafacil.controller;

import javax.servlet.http.HttpServletRequest;

import br.com.projetafacil.model.Classe;
import br.com.projetafacil.repository.Classes;
import br.com.projetafacil.repository.filter.ClasseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import br.com.projetafacil.page.PageWrapper;


@Controller
@RequestMapping("/classes")
public class ClassesController {

	private static final String CLASSE_VIEW_FILE = "_admin/codificacao/classe/CadastroClasse";
	private static final String PESQUISA_CLASSES_FILE = "_admin/codificacao/classe/PesquisaClasses";
	private static final String REDIRECT_NOVA_CLASSE = "redirect:/classes/nova";
	

	@Autowired
	private Classes classes;
	
	/** ETAPA **/
	@RequestMapping("/nova")
	public ModelAndView nova(Classe classe) {
		ModelAndView mv = new ModelAndView(CLASSE_VIEW_FILE);
		return mv;
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(ClasseFilter classeFilter, BindingResult result, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(PESQUISA_CLASSES_FILE);
		PageWrapper<Classe> paginaWrapper = new PageWrapper<>(classes.filtrar(classeFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
	@PostMapping(path="/salvar")
	public ModelAndView salvar(@Validated Classe classe, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return nova(classe);
		}
		try {
			classes.save(classe);
			attributes.addFlashAttribute("mensagem", "Classe salva com sucesso!");
			return new ModelAndView(REDIRECT_NOVA_CLASSE);
		} catch (Exception e) {
			e.printStackTrace();
			return nova(classe);
		}
	}
	
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Classe classe) {
		ModelAndView mv = nova(classe);
		mv.addObject(classe);
		return mv;
	}
	
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			classes.delete(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
}
