package br.com.projetafacil.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.NumberFormat;




@Entity
@Table(name = "orcamento")
@DynamicUpdate
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "preco_total")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal precoTotal;
	
	@Column(name = "valorbdi")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorBDI = BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	
	@OneToMany(mappedBy = "orcamento", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval=true)
	private Set<ItemOrcamento> itensOrcamento = new HashSet<ItemOrcamento>();
	
	@Enumerated(EnumType.STRING)
	private CriterioOrcamento criterio;
	
	
	@ManyToOne
	@JoinColumn(name = "id_cidade")
	private Cidade cidade;
	
	@Transient
	private List<Fornecedor> fornecedores = new ArrayList<>();

	@Transient
	private String uuid;
	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public BigDecimal getPrecoTotal() {
		return this.precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Orcamento other = (Orcamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	public boolean isNovo(){
		return id == null;
	}

	public BigDecimal getValorBDI() {
		return valorBDI;
	}

	public void setValorBDI(BigDecimal valorBDI) {
		this.valorBDI = valorBDI;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void adicionarItens(Set<ItemOrcamento> itensOrcamento) {
		this.setItensOrcamento(itensOrcamento);
		this.getItensOrcamento().forEach(i -> i.setOrcamento(this));
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<ItemOrcamento> getItensOrcamento() {
		return itensOrcamento;
	}

	public void setItensOrcamento(Set<ItemOrcamento> itensOrcamento) {
		this.itensOrcamento = itensOrcamento;
	}

	public CriterioOrcamento getCriterio() {
		return criterio;
	}

	public void setCriterio(CriterioOrcamento criterio) {
		this.criterio = criterio;
	}
	
	
	public void calcularValorTotal() {
		this.precoTotal = calcularValorTotal(getValorTotalItens(), getValorBDI());
	}
	
	private BigDecimal calcularValorTotal(BigDecimal valorTotalItens, BigDecimal valorBDI) {
		BigDecimal valorPercentual = valorTotalItens.setScale(2, BigDecimal.ROUND_HALF_EVEN).divide(new BigDecimal("100")).multiply(Optional.ofNullable(valorBDI).orElse(BigDecimal.ZERO)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal valorTotal = valorTotalItens.add(Optional.ofNullable(valorPercentual).orElse(BigDecimal.ZERO)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return valorTotal;
	}
	
	public BigDecimal getValorTotalItens() {
		return getItensOrcamento().stream()
				.map(ItemOrcamento::getValorTotal)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

	

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}


	
}
