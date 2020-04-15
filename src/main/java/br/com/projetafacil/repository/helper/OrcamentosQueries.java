package br.com.projetafacil.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.projetafacil.model.Orcamento;
import br.com.projetafacil.repository.filter.OrcamentoFilter;

public interface OrcamentosQueries {

	public Page<Orcamento> filtrar(OrcamentoFilter filtro, Pageable pageable);
	
	public Orcamento buscarComItens(Long id);
	
	
	

}
