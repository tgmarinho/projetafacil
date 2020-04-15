package br.com.projetafacil.model.enums;

public enum EnumReferencia {

	SINAPI("SINAPI"),
//	TCPO("TCPO"),
	PROPRIA("PRÓPRIA");
	
	private String descricao;
	
	EnumReferencia(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
