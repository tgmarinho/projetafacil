package br.com.projetafacil.repository.filter;

import br.com.projetafacil.model.Usuario;

public class ComposicaoServicoFilter {

	private String servico;
	private Usuario usuario;
	
	
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
