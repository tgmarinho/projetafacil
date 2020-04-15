package br.com.projetafacil.controller.seguranca;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projetafacil.model.enums.TipoUsuario;
import br.com.projetafacil.security.UsuarioSistema;

@Controller
public class SegurancaController {

	private int contadorDeAcesso = 0;

	@GetMapping("/login")
	public String login(@AuthenticationPrincipal UsuarioSistema user) {
		if (user != null) {
			if(user.getUsuario().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
				return "redirect:/etapas";
			}
			
			return "redirect:/index";
		}
		
		return "Login";
	}
	
	@GetMapping("/")
	public String indexPrincipal() {
		System.out.println("ENTREI NO PROJETA FACIL.COM: " + contadorDeAcesso++);
		return "index2";
	}
	
	
	@GetMapping("/index2")
	public String indexPrincipal2() {
		System.out.println("ENTREI AQUI NA PAGINA INCIAL ROOT / ");
		return "index2";
	}
	
	@GetMapping("/index")
	public String indexSistema(@AuthenticationPrincipal UsuarioSistema user) {
		if (user != null) {
			TipoUsuario usuario = user.getUsuario().getTipoUsuario();
			if(usuario == TipoUsuario.ADMINISTRADOR) {
				return "redirect:/dashboardFornecedorMaterial";
			}
			if(usuario == TipoUsuario.FORNECEDOR_MATERIAL) {
				return "redirect:/dashboardFornecedorMaterial";
			}
			if(usuario == TipoUsuario.FORNECEDOR_MAO_OBRA) {
				return "redirect:/composicoes";
			}
			if(usuario == TipoUsuario.ORCAMENTISTA) {
				return "redirect:/dashboardProjetista";
			}
			
			return "redirect:/";
		}
		return "redirect:/";
	}
	
	@GetMapping("/403")
	public String acessoNegado() {
		return "403";
	}
	
}
