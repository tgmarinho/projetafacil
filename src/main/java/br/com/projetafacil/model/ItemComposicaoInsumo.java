package br.com.projetafacil.model;

import br.com.projetafacil.model.enums.TipoInsumo;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
/*@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_insumo_table")*/
@Table(name = "item_composicao_insumo")
public class ItemComposicaoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NumberFormat(pattern = "#,##0.0000")
    @Column(name = "valor_coeficiente")
    private BigDecimal valorCoeficiente;

    @Column(name = "tipo_insumo")
    @Enumerated(EnumType.STRING)
    private TipoInsumo tipoInsumo;

    // o item pode insumo ou composicao

    @ManyToOne
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;


    @ManyToOne
    @JoinColumn(name = "id_composicao_servico")
    private ComposicaoServico composicaoServico;


    @ManyToOne(targetEntity = ComposicaoServico.class)
    @JoinColumn(name = "id_composicao_composicao")
    private ComposicaoServico composicaoDaComposicao;








    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public ComposicaoServico getComposicaoServico() {
        return composicaoServico;
    }

    public void setComposicaoServico(ComposicaoServico composicaoServico) {
        this.composicaoServico = composicaoServico;
    }

    public BigDecimal getValorCoeficiente() {
        return valorCoeficiente;
    }

    public void setValorCoeficiente(BigDecimal valorCoeficiente) {
        this.valorCoeficiente = valorCoeficiente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return " ID: " + this.id + ", Tipo: " + this.tipoInsumo.getDescricao() + ", Qtde: " + this.getValorCoeficiente() + " \n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((insumo == null) ? 0 : insumo.hashCode());
        result = prime * result + ((composicaoDaComposicao == null) ? 0 : composicaoDaComposicao.hashCode());
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
        ItemComposicaoInsumo other = (ItemComposicaoInsumo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (insumo == null) {
            if (other.insumo != null)
                return false;
        } else if (!insumo.equals(other.insumo))
            return false;
        if (composicaoDaComposicao == null) {
            if (other.composicaoDaComposicao != null)
                return false;
        } else if (!composicaoDaComposicao.equals(other.composicaoDaComposicao))
            return false;
        return true;
    }





    public TipoInsumo getTipoInsumo() {
        return tipoInsumo;
    }

    public void setTipoInsumo(TipoInsumo tipoInsumo) {
        this.tipoInsumo = tipoInsumo;
    }

    public ComposicaoServico getComposicaoDaComposicao() {
        return composicaoDaComposicao;
    }

    public void setComposicaoDaComposicao(ComposicaoServico composicaoDaComposicao) {
        this.composicaoDaComposicao = composicaoDaComposicao;
    }

}
