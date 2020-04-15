package br.com.projetafacil.repository;

import br.com.projetafacil.model.Classe;
import br.com.projetafacil.repository.helper.ClassesQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Classes extends JpaRepository<Classe, Long>, ClassesQueries {


	

	
}
