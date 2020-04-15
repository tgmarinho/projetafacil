package br.com.projetafacil.session;

import br.com.projetafacil.model.*;
import br.com.projetafacil.model.enums.TipoInsumo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class TabelaItensComposicao {

	private String uuid;
	private Set<ItemComposicaoInsumo> itensInsumo = new HashSet<ItemComposicaoInsumo>();
	
	
	public BigDecimal getCoeficienteTotal() {
		 return	this.itensInsumo
				.stream()
				.map(ItemComposicaoInsumo::getValorCoeficiente)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	
	public TabelaItensComposicao(String uuid) {
		this.uuid = uuid;
	}
	

	
	/********************************* Insumo **************************************/
	
	
	
	public void adicionarItem(Insumo insumo, BigDecimal valorCoeficiente) {
		Optional<ItemComposicaoInsumo> itemInsumoOptional = buscarItemPorInsumo(insumo);

		ItemComposicaoInsumo itemComposicaoInsumo = null;
		if (itemInsumoOptional.isPresent()) {
			itemComposicaoInsumo = itemInsumoOptional.get();
			itemComposicaoInsumo.setValorCoeficiente(valorCoeficiente);
		} else {
			itemComposicaoInsumo = new ItemComposicaoInsumo();
			itemComposicaoInsumo.setInsumo(insumo);
			itemComposicaoInsumo.setTipoInsumo(insumo.getTipo());
			itemComposicaoInsumo.setValorCoeficiente(valorCoeficiente);
			getItensInsumo().add(itemComposicaoInsumo);

		}
	}
	
	public void alterarCoeficienteItem(Insumo insumo, BigDecimal valorCoeficiente) {
		ItemComposicaoInsumo itemInsumo = buscarItemPorInsumo(insumo).get();
		itemInsumo.setValorCoeficiente(valorCoeficiente);
	}
	
	
	
	
	public void excluirItem(Insumo insumo) {
		for (ItemComposicaoInsumo item : getItensInsumo()) {
			if(item != null && item.getInsumo() != null) {
				if(item.getInsumo().getId().equals(insumo.getId())){
					getItensInsumo().remove(item);
					break;
				}
			}
		}
		
	}
	
	private Optional<ItemComposicaoInsumo> buscarItemPorInsumo(Insumo insumo) {
		return getItensInsumo().stream()
				.filter(i -> i.getInsumo() != null && i.getInsumo().equals(insumo))
				.findAny();
	}
	
	
	/********************************************** Composição ***********************************************/
	
	public void adicionarItemComposicao(ComposicaoServico composicaoServico, BigDecimal valorCoeficiente) {
		Optional<ItemComposicaoInsumo> itemComposicaoDeComposicaoOptional = buscarItemPorInsumoComposicao(composicaoServico);

		ItemComposicaoInsumo itemComposicaoInsumo = null;
		if (itemComposicaoDeComposicaoOptional.isPresent()) {
			itemComposicaoInsumo = itemComposicaoDeComposicaoOptional.get();
			itemComposicaoInsumo.setValorCoeficiente(valorCoeficiente);
		}
	    else {
	    	itemComposicaoInsumo = new ItemComposicaoInsumo();
	    	itemComposicaoInsumo.setComposicaoDaComposicao(composicaoServico);
	    	itemComposicaoInsumo.setTipoInsumo(TipoInsumo.COMPOSICAO);
	    	itemComposicaoInsumo.setValorCoeficiente(valorCoeficiente);
	    	itensInsumo.add(itemComposicaoInsumo);

		}
	}
	
	
	public void alterarCoeficienteItemComposicao(ComposicaoServico composicaoServico, BigDecimal valorCoeficiente) {
		ItemComposicaoInsumo itemInsumoComposicao = buscarItemPorInsumoComposicao(composicaoServico).get();
		itemInsumoComposicao.setValorCoeficiente(valorCoeficiente);
	}
	
	
	public void excluirItemComposicao(ComposicaoServico composicaoServico) {
		for (ItemComposicaoInsumo item : getItensInsumo()) {
			if(item != null && item.getComposicaoDaComposicao() != null) {
				if(item.getComposicaoDaComposicao().getId().equals(composicaoServico.getId())){
					getItensInsumo().remove(item);
					break;
				}
			}
		}
	}
	
	
	private Optional<ItemComposicaoInsumo> buscarItemPorInsumoComposicao(ComposicaoServico composicaoServico) {
		return itensInsumo.stream()
				.filter(i -> i.getComposicaoDaComposicao() != null && i.getComposicaoDaComposicao().equals(composicaoServico))
				.findAny();
	}
	
	
	
	
	/*************************************** GETTERS  *******************************/

	
	public String getUuid() {
		return uuid;
	}


    public Set<ItemComposicaoInsumo> getItensInsumo()  {
        return this.itensInsumo;
    }


    public Set<ItemComposicaoInsumo> getInsumosComposicao() {
        return this.itensInsumo.stream()
                .filter(item -> item.getTipoInsumo() == TipoInsumo.COMPOSICAO)
                .collect(Collectors.toSet());
    }


    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensComposicao other = (TabelaItensComposicao) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	
}
