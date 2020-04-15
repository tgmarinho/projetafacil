package br.com.projetafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetafacil.model.Orcamento;
import br.com.projetafacil.repository.helper.OrcamentosQueries;

public interface Orcamentos extends JpaRepository<Orcamento, Long>, OrcamentosQueries {

	

}
