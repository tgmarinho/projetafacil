package br.com.projetafacil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetafacil.model.Estado;
import br.com.projetafacil.model.ItemOrcamento;
import br.com.projetafacil.model.Orcamento;
import br.com.projetafacil.repository.Orcamentos;
import br.com.projetafacil.service.material.DetalheMaterialBO;

@Service
public class OrcamentoService {

	@Autowired
	private Orcamentos orcamentos;

	
	@Transactional
	public void salvar(Orcamento orcamento) {
		try {
			orcamentos.save(orcamento);
		} catch (Exception e) {
			throw new IllegalArgumentException("Erro ao salvar o Orçamento: " + e.getMessage());
		}
	}

	public void excluir(Long id) {
		try {
			orcamentos.delete(id);
		} catch (Exception e) {
			throw new IllegalArgumentException("Erro ao excluir o Orçamento: " + e.getMessage());
		}
	}

	public List<Orcamento> filtrar(Orcamento filtro) {
		return orcamentos.findAll();
	}
	
	
	//@TODO ver o que fazer se remover o chamar daqui o 
	public List<DetalheMaterialBO> detalharMaterial(List<ItemOrcamento> itensOrcamento, Estado estado) {
		return null;
	}


	
}
