package br.com.projetafacil.repository.filter;

import br.com.projetafacil.model.Usuario;

public class OrcamentoFilter {
	
	private String descricao;
	private Usuario usuario;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
