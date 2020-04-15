package br.com.projetafacil.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.projetafacil.model.Fornecedor;
import br.com.projetafacil.repository.filter.FornecedorFilter;

public interface FornecedoresQueries {
	
	public Page<Fornecedor> filtrar(FornecedorFilter filtro, Pageable pageable);
	
}
