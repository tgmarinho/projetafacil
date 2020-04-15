package br.com.projetafacil.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import br.com.projetafacil.model.Fornecedor;
import br.com.projetafacil.page.PageWrapper;
import br.com.projetafacil.repository.Fornecedores;
import br.com.projetafacil.repository.filter.FornecedorFilter;
import br.com.projetafacil.security.UsuarioSistema;
import br.com.projetafacil.service.FornecedorService;

@Controller
@RequestMapping("/fornecedores")
public class FornecedoresController {
	
	private static final String FORNECEDOR_VIEW_FILE = "_fornecedor/CadastroFornecedor";
	private static final String PESQUISA_FORNECEDOR_FILE = "_fornecedor/PesquisaFornecedor";
	private static final String REDIRECT_NOVO_FORNECEDOR = "redirect:/fornecedores/novo";
	
	@Autowired
	private Fornecedores fornecedores;
	
	@Autowired
	private FornecedorService fornecedorService;

	
	/** ETAPA **/
	@RequestMapping("/novo")
	public ModelAndView novo(Fornecedor fornecedor) {
		ModelAndView mv = new ModelAndView(FORNECEDOR_VIEW_FILE);
		return mv;
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(FornecedorFilter fornecedorFilter, BindingResult result, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView(PESQUISA_FORNECEDOR_FILE);
		PageWrapper<Fornecedor> paginaWrapper = new PageWrapper<>(fornecedores.filtrar(fornecedorFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	
	@PostMapping(path="/salvar")
	public ModelAndView salvar(@Validated Fornecedor fornecedor, Errors errors, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuario) {
		if (errors.hasErrors()) {
			return novo(fornecedor);
		}
		try {
			
			fornecedor.setUsuario(usuario.getUsuario());
			fornecedorService.salvar(fornecedor);
			attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
			return new ModelAndView(REDIRECT_NOVO_FORNECEDOR);
		} catch (Exception e) {
			e.printStackTrace();
			return novo(fornecedor);
		}
	}
	
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Fornecedor fornecedor) {
		ModelAndView mv = novo(fornecedor);
		mv.addObject(fornecedor);
		return mv;
	}
	
	
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Long id) {
		try {
			fornecedorService.excluir(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	

}
