package br.com.projetafacil.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.model.ItemComposicaoInsumo;


@SessionScope
@Component
public class TabelasItensComposicaoSession {

	private Set<TabelaItensComposicao> tabelas = new HashSet<>();

	
	/** Insumos **/
	public void adicionarItem(String uuid, Insumo insumo, BigDecimal valorCoeficiente) {
		TabelaItensComposicao tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(insumo, valorCoeficiente);
		tabelas.add(tabela);
	}

	public void alterarCoeficienteItemMaterial(String uuid, Insumo insumo, BigDecimal valorCoeficiente) {
		TabelaItensComposicao tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarCoeficienteItem(insumo, valorCoeficiente);
	}

	public void excluirItem(String uuid, Insumo insumo) {
		TabelaItensComposicao tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(insumo);
	}

	public Set<ItemComposicaoInsumo> getItensInsumo(String uuid) {
		return buscarTabelaPorUuid(uuid).getItensInsumo();
	}
	
	public Set<ItemComposicaoInsumo> getItensInsumoComposicao(String uuid) {
		return buscarTabelaPorUuid(uuid).getInsumosComposicao();
	}
	
	
	public void adicionarItemComposicao(String uuid, ComposicaoServico composicaoServico, BigDecimal valorCoeficiente) {
		TabelaItensComposicao tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItemComposicao(composicaoServico, valorCoeficiente);
		tabelas.add(tabela);
	}

	public void alterarCoeficienteItemComposicao(String uuid, ComposicaoServico composicaoServico, BigDecimal valorCoeficiente) {
		TabelaItensComposicao tabela = buscarTabelaPorUuid(uuid);
		tabela.alterarCoeficienteItemComposicao(composicaoServico, valorCoeficiente);
	}

	public void excluirItemComposicao(String uuid, ComposicaoServico composicaoServico) {
		TabelaItensComposicao tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItemComposicao(composicaoServico);
	}

	
	
	
	/** Comum */	
	
	private TabelaItensComposicao buscarTabelaPorUuid(String uuid) {
		TabelaItensComposicao tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensComposicao(uuid));
		return tabela;
	}
	

	
}
