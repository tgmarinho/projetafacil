package br.com.projetafacil.model;

public enum CriterioOrcamento {

	MENOR_PRECO("Menor Preço"),
	PRECO_MEDIO("Preço Médio"),
	MAIOR_PRECO("Maior Preço");
	
	private String descricao;
	
	CriterioOrcamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
