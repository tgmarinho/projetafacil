package br.com.projetafacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DashboardProjetistaController {

	
	@GetMapping("/dashboardProjetista")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("/_projetista/Dashboard");
		
		
		return mv;
	}
	
}
