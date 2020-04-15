package br.com.projetafacil.service;

import br.com.projetafacil.model.Classe;
import br.com.projetafacil.repository.Classes;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClasseService {

	@Autowired
	private Classes classes;
	
	
	public void salvar(Classe classe) {
		try {
			classes.saveAndFlush(classe);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Long id) {
		try{
			classes.delete(id);
		} catch(javax.validation.ConstraintViolationException | ConstraintViolationException | DataIntegrityViolationException e){
			e.printStackTrace();
			throw new RuntimeException("Ops! Não foi possível deletar a classe devido a um relacionamento com a mesma tem com o sistema");
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Ops! Não foi possível deletar a classe! O Administrador do sistema irá verificar.");
		}
		
	}

	public List<Classe> filtrar(Classe filtro) {
		return classes.findAll();
	}
	





}