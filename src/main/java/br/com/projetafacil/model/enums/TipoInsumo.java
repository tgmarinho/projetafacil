package br.com.projetafacil.model.enums;

public enum TipoInsumo {

	
	MATERIAL("Material"), 
	EQUIPAMENTO("Equipamento"), 
	MAODEOBRA("Mão de Obra"),
	COMPOSICAO("Composição");
	

	private String descricao;
	
	TipoInsumo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
