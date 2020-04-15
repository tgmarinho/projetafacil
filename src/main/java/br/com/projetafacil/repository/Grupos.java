package br.com.projetafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetafacil.model.Grupo;


public interface Grupos extends JpaRepository<Grupo, Long> {

}
