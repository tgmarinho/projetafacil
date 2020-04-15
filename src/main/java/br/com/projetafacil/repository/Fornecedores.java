package br.com.projetafacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetafacil.model.Fornecedor;
import br.com.projetafacil.repository.helper.FornecedoresQueries;

public interface Fornecedores extends JpaRepository<Fornecedor, Long>, FornecedoresQueries {

	public List<Fornecedor> findByUsuarioId(Long id);
	
}
