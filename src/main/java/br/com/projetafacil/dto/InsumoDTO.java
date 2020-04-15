package br.com.projetafacil.dto;

public class InsumoDTO {

	private Long id;
	private String nome;
	private String codigoBIM;
	
	
	
	public InsumoDTO(Long id, String nome, String codigoBIM) {
		this.id = id;
		this.nome = nome;
		this.codigoBIM = codigoBIM;
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
	
	
}
