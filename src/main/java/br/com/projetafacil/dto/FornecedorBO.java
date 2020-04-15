package br.com.projetafacil.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class FornecedorBO {
 
	private String nome;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal preco;
	
//	@NumberFormat(pattern = "#,##0.00")
//	private BigDecimal quantidade;
	
	
	public FornecedorBO() { }
	
	public FornecedorBO(Double preco) {
		this.preco = BigDecimal.valueOf(preco == null ? 0.0 : preco.doubleValue());
	}
	
	public FornecedorBO(String nome, BigDecimal preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public FornecedorBO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome == null ? "Não Encontrado" : nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public BigDecimal getPreco() {
		return preco ;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
}
