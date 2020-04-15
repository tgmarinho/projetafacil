package br.com.projetafacil.model.enums;

public enum TipoUsuario {

	ADMINISTRADOR("Administrador"),
	FORNECEDOR_MATERIAL("Fornecedor"),
	FORNECEDOR_MAO_OBRA("Mão de Obra"),
	ORCAMENTISTA("Usuário Projeta Fácil");
	
	private String descricao;
	
	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
}
