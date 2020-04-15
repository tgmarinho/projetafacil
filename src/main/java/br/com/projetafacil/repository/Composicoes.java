package br.com.projetafacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.repository.helper.ComposicoesQueries;

public interface Composicoes extends JpaRepository<ComposicaoServico, Long>, ComposicoesQueries {


	@Query("SELECT max(s.codigo) FROM ComposicaoServico s JOIN s.classe classe JOIN s.usuario usuario WHERE s.classe.id = ?1 AND s.usuario.id = ?2")
	String findByServicoPorClasseEUsuario(Long idClasse, Long idUsuario);

	

}
