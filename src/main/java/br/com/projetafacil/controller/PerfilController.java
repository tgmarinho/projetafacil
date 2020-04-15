package br.com.projetafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.projetafacil.model.Usuario;
import br.com.projetafacil.repository.Perfis;
import br.com.projetafacil.repository.Usuarios;
import br.com.projetafacil.security.UsuarioSistema;
import br.com.projetafacil.service.UsuarioService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	private static String REDIRECT_PERFIL = "redirect:/perfil";
	private static String PERFIL = "/_geral/Perfil";
	
	@Autowired
	Perfis perfis;
	
	@Autowired 
	Usuarios usuarios;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public ModelAndView perfil(@AuthenticationPrincipal UsuarioSistema user) {
		
		ModelAndView mv = new ModelAndView(PERFIL);
		mv.addObject("usuario", usuarios.buscarComGrupos(user.getUsuario().getId()));
		return mv;
	}

	@PostMapping("/salvar")
	public ModelAndView salvar(Usuario usuario, BindingResult result, RedirectAttributes attributes, Errors errors, @AuthenticationPrincipal UsuarioSistema user) {

		try {
			usuarioService.salvarPerfil(usuario, user);
			user.getUsuario().setNome(usuario.getNome());
			attributes.addFlashAttribute("mensagem", "Perfil salvo com sucesso.");
			return new ModelAndView(REDIRECT_PERFIL);
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagemErro", "Erro ao salvar o perfil" + e.getMessage());
			return new ModelAndView(REDIRECT_PERFIL);
		}
		
	}
	
	
}
