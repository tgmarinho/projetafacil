package br.com.projetafacil.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Telefone {

	
	@Column(name = "ddd_fixo")
	private String dddFixo;
	
	@Column(name = "telefone_fixo")
	private String fixo;
	
	@Column(name = "ddd_celular")
	private String dddCelular;
	
	@Column(name = "celular")
	private String celular;


	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	public String getDddFixo() {
		return dddFixo;
	}

	public void setDddFixo(String dddFixo) {
		this.dddFixo = dddFixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}
	
}
