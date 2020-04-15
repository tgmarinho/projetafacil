package br.com.projetafacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.repository.Insumos;

@Service
public class InsumosService {

	@Autowired
	private Insumos insumos;
	
	
	public void salvar(Insumo insumo) {
		try {
			insumos.save(insumo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Ops! Ocorreu um erro ao salvar:" + e.getMessage());
		}
	}

	@Transactional
	public void excluir(Long id) {
		try{
			
			
//			insumo.setEtapas(null);
//			insumo.setSubEtapas(null);
			
//			insumos.saveAndFlush(insumo);
			
			insumos.delete(id);
			
		} catch(Exception err){
			err.printStackTrace();
			throw new RuntimeException("Ops! Não foi possível deletar o insumo devido a um relacionamento outro item no sistema");
		}
	}

	
	
}
