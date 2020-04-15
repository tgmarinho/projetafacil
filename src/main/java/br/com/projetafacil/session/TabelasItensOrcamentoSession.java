package br.com.projetafacil.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.projetafacil.dto.DetalheMaoDeObraPropriaBO;
import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.ItemOrcamento;



@SessionScope
@Component
public class TabelasItensOrcamentoSession {

	private Set<TabelaItensOrcamento> tabelas = new HashSet<TabelaItensOrcamento>();

	
	public void adicionarItem(String uuid, ComposicaoServico composicaoServico, BigDecimal quantidade) {
		TabelaItensOrcamento tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(composicaoServico, quantidade);
		tabelas.add(tabela);
	}

	public void alterarQuantidadeItem(String uuid, ComposicaoServico composicaoServico, BigDecimal quantidade) {
		TabelaItensOrcamento tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarQuantidadeItem(composicaoServico, quantidade);
	}

	public void excluirItem(String uuid, ComposicaoServico composicaoServico) {
		TabelaItensOrcamento tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(composicaoServico);
	}

	public List<ItemOrcamento> getItensOrcamento(String uuid) {
		return buscarTabelaPorUuid(uuid).getItensOrcamento();
	}
	
	private TabelaItensOrcamento buscarTabelaPorUuid(String uuid) {
		TabelaItensOrcamento tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensOrcamento(uuid));
		return tabela;
	}
	
	
	public List<ComposicaoServico> buscarComposicaoDosItens(String uuid){
		return buscarTabelaPorUuid(uuid).buscarComposicaoDosItensDeOrcamento();
	}


	public List<DetalheMaoDeObraPropriaBO> detalharMaoDeObra(String uuid) {
		return buscarTabelaPorUuid(uuid).detalharMaoDeObraPropria();
	}
	
	public BigDecimal getValorTotal(String uuid) {
		return buscarTabelaPorUuid(uuid).getValorTotal();
	}

	
}
