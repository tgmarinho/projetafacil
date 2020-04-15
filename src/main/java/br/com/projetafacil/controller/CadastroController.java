package br.com.projetafacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cadastrar")
public class CadastroController {

	private static String CADASTRO = "Cadastro";
	
	@GetMapping
	public String cadastrar() {
	
		return CADASTRO;
	}
	
}
