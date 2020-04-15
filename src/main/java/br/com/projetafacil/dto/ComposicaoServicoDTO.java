package br.com.projetafacil.dto;

import br.com.projetafacil.model.enums.EnumReferencia;
import br.com.projetafacil.model.enums.EnumUnidade;

public class ComposicaoServicoDTO {

	private Long id;
	private String nome;
	private String codigoBIM;
	private EnumReferencia referencia;
	private EnumUnidade unidade;
	
	
	public ComposicaoServicoDTO(Long id, String nome, EnumReferencia referencia, EnumUnidade unidade) {
		this.id = id;
		this.nome = nome;
		this.referencia = referencia;
		this.unidade = unidade;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoBIM() {
		return codigoBIM;
	}

	public void setCodigoBIM(String codigoBIM) {
		this.codigoBIM = codigoBIM;
	}

	public EnumReferencia getReferencia() {
		return referencia;
	}

	public void setReferencia(EnumReferencia referencia) {
		this.referencia = referencia;
	}

	public String getUnidade() {
		return unidade.getDescricao();
	}

	public void setUnidade(EnumUnidade unidade) {
		this.unidade = unidade;
	}

	
	
	
}
