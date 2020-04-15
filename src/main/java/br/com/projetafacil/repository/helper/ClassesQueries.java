package br.com.projetafacil.repository.helper;

import br.com.projetafacil.model.Classe;
import br.com.projetafacil.repository.filter.ClasseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassesQueries {
	

	public Page<Classe> filtrar(ClasseFilter filtro, Pageable pageable);
	
}
