package br.com.projetafacil.dto;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class DetalheMaoDeObraPropriaBO {

	private Long id;
	private String codigoBIM;
	private String maoDeObra;
	private String unidade;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valor;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal quantidade;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoBIM() {
		return codigoBIM;
	}
	public void setCodigoBIM(String codigoBIM) {
		this.codigoBIM = codigoBIM;
	}
	public String getMaoDeObra() {
		return maoDeObra;
	}
	public void setMaoDeObra(String maoDeObra) {
		this.maoDeObra = maoDeObra;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	@Override
	public String toString() {
		return "DetalheMaoDeObraPropriaBO [id=" + id + ", codigoBIM=" + codigoBIM + ", maoDeObra=" + maoDeObra
				+ ", unidade=" + unidade + ", valor=" + valor + ", consumo=" + getQuantidade() + "]";
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@NumberFormat(pattern = "#,##0.00")
	public BigDecimal getValorTotal() {
		return quantidade.multiply(valor).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	
}
