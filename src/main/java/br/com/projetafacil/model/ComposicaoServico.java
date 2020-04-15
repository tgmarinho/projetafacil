package br.com.projetafacil.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.projetafacil.model.enums.EnumReferencia;
import br.com.projetafacil.model.enums.EnumUnidade;
import br.com.projetafacil.model.enums.TipoInsumo;

@Entity
@Table(name="composicao_servico")
@DynamicUpdate
public class ComposicaoServico implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=999)
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "codigo_bim")
	private String codigoBIM;
	
	@Column(name = "unidade")
	@Enumerated(EnumType.STRING)
	private EnumUnidade unidade = EnumUnidade.UNITARIO;

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	@ManyToOne
	@JoinColumn(name = "id_classe")
	@JsonIgnore
	private Classe classe;
			
	@Enumerated(EnumType.STRING)
	private EnumReferencia referencia;

	@OneToMany(mappedBy = "composicaoServico", cascade = {CascadeType.ALL,  CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval=true)
	private Set<ItemComposicaoInsumo> insumos =  new HashSet<ItemComposicaoInsumo>();
	
//	@OneToMany(mappedBy = "composicaoServico", cascade = {CascadeType.ALL,  CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval=true)
//	private Set<ItemComposicaoComposicao> composicoes =  new HashSet<ItemComposicaoComposicao>();
	
		
	/**TODO metodo de teste para pegar apenas items que sao materiais da composicao **/
	public List<ItemComposicaoInsumo> getItensInsumosMateriais() {
		return insumos.stream()
				.filter(insumoMaterial -> (insumoMaterial.getInsumo().getTipo() == TipoInsumo.MATERIAL))
				.collect(Collectors.toList());
	}




	
	// Metodo usado apenas para teste
	public void adicionaItem(ItemComposicaoInsumo insumo) {
		this.insumos.add(insumo);
	}
		
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	
	@Transient
	private String uuid;
	
	@Transient
	private String codigoInteiro;
	
	public boolean isNova() {
		return id == null;
	}
	
	
	public void adicionarItens(Set<ItemComposicaoInsumo> insumos) {
		this.setInsumos(insumos);
		this.getInsumos().forEach(i -> i.setComposicaoServico(this));

//		this.setComposicoes(composicoes);
//		this.getComposicoes().forEach(i -> i.setComposicaoServico(this));
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
		ComposicaoServico other = (ComposicaoServico) obj;
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

	public EnumUnidade getUnidade() {
		return unidade;
	}

	public void setUnidade(EnumUnidade unidade) {
		this.unidade = unidade;
	}

				
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	



	public EnumReferencia getReferencia() {
		return referencia;
	}

	public void setReferencia(EnumReferencia referencia) {
		this.referencia = referencia;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getCodigoBIM() {
		return codigoBIM;
	}


	public void setCodigoBIM(String codigoBIM) {
		this.codigoBIM = codigoBIM;
	}

	public String getCodigoInteiro() {
//		return subEtapa.getEtapa().getCodigo().concat(subEtapa.getCodigo()).concat(codigo);
		return classe.getSigla() + "." +  codigo;
	}


	public Set<ItemComposicaoInsumo> getInsumos() {
		return insumos;
	}


	public void setInsumos(Set<ItemComposicaoInsumo> insumos) {
		this.insumos = insumos;
	}

	public Set<ItemComposicaoInsumo> getInsumosComposicao() {
		return this.insumos.stream()
				.filter(item -> item.getTipoInsumo() == TipoInsumo.COMPOSICAO)
				.collect(Collectors.toSet());
	}


//	public Set<ItemComposicaoComposicao> getComposicoes() {
//		return composicoes;
//	}
//
//
//	public void setComposicoes(Set<ItemComposicaoComposicao> composicoes) {
//		this.composicoes = composicoes;
//	}




	
}
