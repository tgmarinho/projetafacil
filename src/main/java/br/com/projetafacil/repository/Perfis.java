package br.com.projetafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.projetafacil.model.Perfil;

public interface Perfis extends JpaRepository<Perfil, Long>  {

	@Query("SELECT p FROM Perfil p WHERE p.usuario.id = ?1")
	public Perfil buscarPorIdUsuario(Long id);
	
	
}
