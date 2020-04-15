package br.com.projetafacil.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.projetafacil.model.Fornecedor;
import br.com.projetafacil.repository.Fornecedores;


@Service
public class FornecedorService {

	@Autowired
	private Fornecedores fornecedores;
	
	
	public void salvar(Fornecedor fornecedor) {
		try {
			fornecedores.saveAndFlush(fornecedor);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(Long codigo) {
		try{
			fornecedores.delete(codigo);
		} catch(javax.validation.ConstraintViolationException | ConstraintViolationException | DataIntegrityViolationException e){
			e.printStackTrace();
			throw new RuntimeException("Ops! Não foi possível deletar a fornecedor devido a um relacionamento com a mesma tem com o sistema");
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Ops! Não foi possível deletar a fornecedor! O Administrador do sistema irá verificar.");
		}
		
	}


}