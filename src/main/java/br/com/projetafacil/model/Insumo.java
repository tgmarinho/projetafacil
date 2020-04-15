package br.com.projetafacil.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.projetafacil.model.enums.EnumUnidade;
import br.com.projetafacil.model.enums.TipoInsumo;

@Entity
@Table(name = "insumo")
@DynamicUpdate
public class Insumo implements Serializable  {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=999)
	@NotEmpty(message= "Nome é obrigatório")
	private String nome;
	
	@NotNull(message = "Unidade deve ser selecionada")
	@Enumerated(EnumType.STRING)
	private EnumUnidade unidade;
	
	@Enumerated(EnumType.STRING)
	private TipoInsumo tipo;
	

	@Column(name="codigo_bim")
	private String codigoBIM;
	
	@Column(name="codigo_sinapi")
	private String codigoSINAPI;
	

	
	@Column(name = "valor_al")
	private BigDecimal valorAL;
	@Column(name = "valor_ap")
	private BigDecimal valorAP;
	@Column(name = "valor_ac")
	private BigDecimal valorAC;
	@Column(name = "valor_aa")
	private BigDecimal valorAA;
	@Column(name = "valor_ba")
	private BigDecimal valorBA;
	@Column(name = "valor_ce")
	private BigDecimal valorCE;
	@Column(name = "valor_df")
	private BigDecimal valorDF;
	@Column(name = "valor_es")
	private BigDecimal valorES;
	@Column(name = "valor_go")
	private BigDecimal valorGO;
	@Column(name = "valor_ma")
	private BigDecimal valorMA;
	@Column(name = "valor_mt")
	private BigDecimal valorMT;
	@Column(name = "valor_ms")
	private BigDecimal valorMS;
	@Column(name = "valor_mg")
	private BigDecimal valorMG;
	@Column(name = "valor_pa")
	private BigDecimal valorPA;
	@Column(name = "valor_pb")
	private BigDecimal valorPB;
	@Column(name = "valor_pr")
	private BigDecimal valorPR;
	@Column(name = "valor_pe")
	private BigDecimal valorPE;
	@Column(name = "valor_pi")
	private BigDecimal valorPI;
	@Column(name = "valor_rj")
	private BigDecimal valorRJ;
	@Column(name = "valor_rn")
	private BigDecimal valorRN;
	@Column(name = "valor_rs")
	private BigDecimal valorRS;
	@Column(name = "valor_ro")
	private BigDecimal valorRO;
	@Column(name = "valor_rr")
	private BigDecimal valorRR;
	@Column(name = "valor_sc")
	private BigDecimal valorSC;
	@Column(name = "valor_sp")
	private BigDecimal valorSP;
	@Column(name = "valor_se")
	private BigDecimal valorSE;
	@Column(name = "valor_to")
	private BigDecimal valorTO;
	

	public boolean isNovo(){
		return id == null;
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
		Insumo other = (Insumo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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



	public EnumUnidade getUnidade() {
		return unidade;
	}



	public void setUnidade(EnumUnidade unidade) {
		this.unidade = unidade;
	}



	public TipoInsumo getTipo() {
		return tipo;
	}



	public void setTipo(TipoInsumo tipo) {
		this.tipo = tipo;
	}



	public String getCodigoBIM() {
		return codigoBIM;
	}



	public void setCodigoBIM(String codigoBIM) {
		this.codigoBIM = codigoBIM;
	}



	public BigDecimal getValorAL() {
		return valorAL;
	}



	public void setValorAL(BigDecimal valorAL) {
		this.valorAL = valorAL;
	}



	public BigDecimal getValorAP() {
		return valorAP;
	}



	public void setValorAP(BigDecimal valorAP) {
		this.valorAP = valorAP;
	}



	public BigDecimal getValorAC() {
		return valorAC;
	}



	public void setValorAC(BigDecimal valorAC) {
		this.valorAC = valorAC;
	}



	public BigDecimal getValorAA() {
		return valorAA;
	}



	public void setValorAA(BigDecimal valorAA) {
		this.valorAA = valorAA;
	}



	public BigDecimal getValorBA() {
		return valorBA;
	}



	public void setValorBA(BigDecimal valorBA) {
		this.valorBA = valorBA;
	}



	public BigDecimal getValorCE() {
		return valorCE;
	}



	public void setValorCE(BigDecimal valorCE) {
		this.valorCE = valorCE;
	}



	public BigDecimal getValorDF() {
		return valorDF;
	}



	public void setValorDF(BigDecimal valorDF) {
		this.valorDF = valorDF;
	}



	public BigDecimal getValorES() {
		return valorES;
	}



	public void setValorES(BigDecimal valorES) {
		this.valorES = valorES;
	}



	public BigDecimal getValorGO() {
		return valorGO;
	}



	public void setValorGO(BigDecimal valorGO) {
		this.valorGO = valorGO;
	}



	public BigDecimal getValorMA() {
		return valorMA;
	}



	public void setValorMA(BigDecimal valorMA) {
		this.valorMA = valorMA;
	}



	public BigDecimal getValorMT() {
		return valorMT;
	}



	public void setValorMT(BigDecimal valorMT) {
		this.valorMT = valorMT;
	}



	public BigDecimal getValorMS() {
		return valorMS;
	}



	public void setValorMS(BigDecimal valorMS) {
		this.valorMS = valorMS;
	}



	public BigDecimal getValorMG() {
		return valorMG;
	}



	public void setValorMG(BigDecimal valorMG) {
		this.valorMG = valorMG;
	}



	public BigDecimal getValorPA() {
		return valorPA;
	}



	public void setValorPA(BigDecimal valorPA) {
		this.valorPA = valorPA;
	}



	public BigDecimal getValorPB() {
		return valorPB;
	}



	public void setValorPB(BigDecimal valorPB) {
		this.valorPB = valorPB;
	}



	public BigDecimal getValorPR() {
		return valorPR;
	}



	public void setValorPR(BigDecimal valorPR) {
		this.valorPR = valorPR;
	}



	public BigDecimal getValorPE() {
		return valorPE;
	}



	public void setValorPE(BigDecimal valorPE) {
		this.valorPE = valorPE;
	}



	public BigDecimal getValorPI() {
		return valorPI;
	}



	public void setValorPI(BigDecimal valorPI) {
		this.valorPI = valorPI;
	}



	public BigDecimal getValorRJ() {
		return valorRJ;
	}



	public void setValorRJ(BigDecimal valorRJ) {
		this.valorRJ = valorRJ;
	}



	public BigDecimal getValorRN() {
		return valorRN;
	}



	public void setValorRN(BigDecimal valorRN) {
		this.valorRN = valorRN;
	}



	public BigDecimal getValorRS() {
		return valorRS;
	}



	public void setValorRS(BigDecimal valorRS) {
		this.valorRS = valorRS;
	}



	public BigDecimal getValorRO() {
		return valorRO;
	}



	public void setValorRO(BigDecimal valorRO) {
		this.valorRO = valorRO;
	}

	public BigDecimal getValorRR() {
		return valorRR;
	}

	public void setValorRR(BigDecimal valorRR) {
		this.valorRR = valorRR;
	}

	public BigDecimal getValorSC() {
		return valorSC;
	}

	public void setValorSC(BigDecimal valorSC) {
		this.valorSC = valorSC;
	}

	public BigDecimal getValorSP() {
		return valorSP;
	}

	public void setValorSP(BigDecimal valorSP) {
		this.valorSP = valorSP;
	}

	public BigDecimal getValorSE() {
		return valorSE;
	}

	public void setValorSE(BigDecimal valorSE) {
		this.valorSE = valorSE;
	}

	public BigDecimal getValorTO() {
		return valorTO;
	}

	public void setValorTO(BigDecimal valorTO) {
		this.valorTO = valorTO;
	}



	public String getCodigoSINAPI() {
		return codigoSINAPI;
	}



	public void setCodigoSINAPI(String codigoSINAPI) {
		this.codigoSINAPI = codigoSINAPI;
	}




}
