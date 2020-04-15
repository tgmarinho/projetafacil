package br.com.projetafacil.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "item_orcamento")
@DynamicUpdate
public class ItemOrcamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name="quantidade")
	private BigDecimal quantidade;

	@OneToOne
	@JoinColumn(name = "id_composicao_servico")
	private ComposicaoServico composicaoServico;

	@ManyToOne
	@JoinColumn(name = "id_orcamento")
	private Orcamento orcamento;
	
	@Column(name="valor_material")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorMaterial = BigDecimal.ZERO;

	@Column(name="valor_mao_de_obra")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorMaoDeObra = BigDecimal.ZERO;
	
	@Column(name="valor_equipamento")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorEquipamento = BigDecimal.ZERO;
	
	@Column(name="valor_unitario")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	
	@Column(name="valor_total")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	
	
	
	
	@Override
	public String toString() {
		return "ItemOrcamento [id=" + id + ", quantidade=" + quantidade + ", composicaoServico=" + composicaoServico
				+ ", orcamento=" + orcamento + ", valorMaterial=" + valorMaterial + ", valorMaoDeObra=" + valorMaoDeObra
				+ ", valorEquipamento=" + valorEquipamento + ", valorUnitario=" + valorUnitario + ", valorTotal="
				+ valorTotal + "]";
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ComposicaoServico getComposicaoServico() {
		return this.composicaoServico;
	}

	public void setComposicaoServico(ComposicaoServico composicaoServico) {
		this.composicaoServico = composicaoServico;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((composicaoServico == null) ? 0 : composicaoServico.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrcamento other = (ItemOrcamento) obj;
		if (composicaoServico == null) {
			if (other.composicaoServico != null)
				return false;
		} else if (!composicaoServico.equals(other.composicaoServico))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public BigDecimal getValorMaterial() {
		return valorMaterial;
	}

	public void setValorMaterial(BigDecimal valorMaterial) {
		this.valorMaterial = valorMaterial;
	}

	public BigDecimal getValorMaoDeObra() {
		return valorMaoDeObra;
	}

	public void setValorMaoDeObra(BigDecimal valorMaoDeObra) {
		this.valorMaoDeObra = valorMaoDeObra;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorEquipamento() {
		return valorEquipamento;
	}

	public void setValorEquipamento(BigDecimal valorEquipamento) {
		this.valorEquipamento = valorEquipamento;
	}


}
