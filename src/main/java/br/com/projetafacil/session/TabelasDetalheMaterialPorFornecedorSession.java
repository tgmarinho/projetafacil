package br.com.projetafacil.session;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;



@SessionScope
@Component
public class TabelasDetalheMaterialPorFornecedorSession {

	private Set<TabelaItensDetalheMaterialPorFornecedor> tabelas = new HashSet<TabelaItensDetalheMaterialPorFornecedor>();

	
	

	
}
