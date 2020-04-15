package br.com.projetafacil.repository.filter;

import br.com.projetafacil.model.enums.TipoInsumo;

public class InsumoFilter {

	private String nome;

	private TipoInsumo tipo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoInsumo getTipo() {
		return tipo;
	}

	public void setTipo(TipoInsumo tipo) {
		this.tipo = tipo;
	}

	

	
}
