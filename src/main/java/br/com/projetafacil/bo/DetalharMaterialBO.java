package br.com.projetafacil.bo;

import java.util.List;
import java.util.Optional;

import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.ItemOrcamento;

public class DetalharMaterialBO {
	
	
	
	
	
	
	
	
	
	private Optional<ItemOrcamento> buscarItemPorComposicaoServico(ComposicaoServico composicaoServico, List<ItemOrcamento> itensOrcamento) {
		return itensOrcamento.stream()
				.filter(i -> i.getComposicaoServico().equals(composicaoServico))
				.findAny();
	}
	

}
