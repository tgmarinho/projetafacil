package br.com.projetafacil.session;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.projetafacil.dto.DetalheMaoDeObraPropriaBO;
import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.ItemOrcamento;


public class TabelaItensOrcamento {

	private String uuid;
	private List<ItemOrcamento> itensOrcamento = new ArrayList<ItemOrcamento>();
	
	List<DetalheMaoDeObraPropriaBO> maoDeObraDetalhados; 
	
	
	public TabelaItensOrcamento(String uuid) {
		this.uuid = uuid;
	}

	
	public BigDecimal getValorTotal() {
		return getItensOrcamento().stream()
				.map(ItemOrcamento::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
	
	
	public void adicionarItem(ComposicaoServico composicaoServico, BigDecimal quantidade) {
		Optional<ItemOrcamento> itemOrcamentoOptional = buscarItemPorComposicaoServico(composicaoServico);

		ItemOrcamento itemOrcamento = null;
		if (itemOrcamentoOptional.isPresent()) {
			itemOrcamento = itemOrcamentoOptional.get();
			itemOrcamento.setQuantidade(quantidade.add(itemOrcamento.getQuantidade()));
		} else {
			itemOrcamento = new ItemOrcamento();
			itemOrcamento.setComposicaoServico(composicaoServico);
			itemOrcamento.setQuantidade(quantidade);
			getItensOrcamento().add(itemOrcamento);
		}
	}
	
	
	public void alterarQuantidadeItem(ComposicaoServico composicaoServico, BigDecimal quantidade) {
		ItemOrcamento itemOrcamento = buscarItemPorComposicaoServico(composicaoServico).get();
		itemOrcamento.setQuantidade(quantidade);
	}
	
	
	
	
	public void excluirItem(ComposicaoServico composicaoServico) {
		for (ItemOrcamento item : getItensOrcamento()) {
			if(item != null && item.getComposicaoServico() != null) {
				if(item.getComposicaoServico().getId().equals(composicaoServico.getId())){
					getItensOrcamento().remove(item);
					break;
				}
			}
		}
		
	}
	
	private Optional<ItemOrcamento> buscarItemPorComposicaoServico(ComposicaoServico composicaoServico) {
		return getItensOrcamento().stream()
				.filter(i -> i.getComposicaoServico().equals(composicaoServico))
				.findAny();
	}
	
	public List<ComposicaoServico> buscarComposicaoDosItensDeOrcamento() {
				 return itensOrcamento.stream().map(ItemOrcamento::getComposicaoServico).collect(Collectors.toList());
	}
	
	public List<DetalheMaoDeObraPropriaBO> detalharMaoDeObraPropria() {
		List<ComposicaoServico> composicoes = buscarComposicaoDosItensDeOrcamento();
		
		
		maoDeObraDetalhados = new ArrayList<DetalheMaoDeObraPropriaBO>();
		
//		composicoes.stream()
//				   .filter(composicao -> !composicao.getMaoDeObras().isEmpty())
//				   .forEach(composicaoComMaoDeObra -> composicaoComMaoDeObra.getMaoDeObras()
//						         .stream()
//						         .forEach(itemMaoDeObraComposicao -> {
//
//						        	 Optional<ItemOrcamento> io =  buscarItemPorComposicaoServico(itemMaoDeObraComposicao.getComposicaoServico());
//							        	
//						        	 Optional<DetalheMaoDeObraPropriaBO> dmOptional =  maoDeObraDetalhados.stream()
//						        			 					.filter(maoDeObra -> maoDeObra.getId() == itemMaoDeObraComposicao.getInsumoMaoDeObra().getId())
//						        			 					.findAny();
//						        	 DetalheMaoDeObraPropriaBO dm = null;
//									 if (dmOptional.isPresent()) {
//										dm = dmOptional.get();
//										dm.setQuantidade(itemMaoDeObraComposicao.getValorCoeficiente().multiply(io.get().getQuantidade()).add(dm.getQuantidade()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
//									} else {
//										dm = new DetalheMaoDeObraPropriaBO();
//										dm.setId(itemMaoDeObraComposicao.getInsumoMaoDeObra().getId());
//										dm.setCodigoBIM(itemMaoDeObraComposicao.getInsumoMaoDeObra().getCodigoBIM());
//										dm.setMaoDeObra(itemMaoDeObraComposicao.getInsumoMaoDeObra().getNome());
//										dm.setUnidade(itemMaoDeObraComposicao.getInsumoMaoDeObra().getUnidade().getDescricao());
//										dm.setValor(itemMaoDeObraComposicao.getValorTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN));
//										dm.setQuantidade(itemMaoDeObraComposicao.getValorCoeficiente().multiply(io.get().getQuantidade()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
//										maoDeObraDetalhados.add(dm);
//									}
//						        	 
//						         }));
		
		return maoDeObraDetalhados;
	}
	
	
	
	
	public List<ItemOrcamento> getItensOrcamento() {
		return itensOrcamento;
	}


	public String getUuid() {
		return uuid;
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
		TabelaItensOrcamento other = (TabelaItensOrcamento) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

	
}
