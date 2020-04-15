package br.com.projetafacil.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.repository.filter.InsumoFilter;

public interface InsumosQueries {
	
	public Page<Insumo> filtrar(InsumoFilter filtro, Pageable pageable);
	


	
}
