package br.com.projetafacil.service.material;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.NumberFormat;

import br.com.projetafacil.dto.FornecedorBO;

public class DetalheMaterialBO {

	private Long id;
	private String codigoBIM;
	private String material;
	private String unidade;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorSinapi;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorTotalSinapi;
	
	
	
	
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal quantidade;
	
	private List<FornecedorBO> fornecedores = new ArrayList<>();

	public DetalheMaterialBO() { 	}
	
	
	
	@Override
	public String toString() {
		return " - " + material + " custa " + "R$ " + valorSinapi + " e tem: " + quantidade + " no pedido \n";
	}
	
	
	public String getCodigoBIM() {
		return codigoBIM;
	}
	public void setCodigoBIM(String codigoBIM) {
		this.codigoBIM = codigoBIM;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public List<FornecedorBO> getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(List<FornecedorBO> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorSinapi() {
		return valorSinapi;
	}

	public void setValorSinapi(BigDecimal valorSinapi) {
		this.valorSinapi = valorSinapi;
	}

	public BigDecimal getValorTotalSinapi() {
		return valorTotalSinapi;
	}

	public void setValorTotalSinapi(BigDecimal valorTotalSinapi) {
		this.valorTotalSinapi = valorTotalSinapi;
	}
	

	
	
	
	
	
	
}
