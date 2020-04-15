package br.com.projetafacil.session;

import br.com.projetafacil.model.*;
import br.com.projetafacil.model.enums.EnumUnidade;
import br.com.projetafacil.model.enums.TipoInsumo;
import br.com.projetafacil.service.material.DetalheMaterialBO;
import br.com.projetafacil.service.material.DetalheMaterialService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TabelaItensOrcamentoTest {

	private TabelaItensOrcamento tabelaItensOrcamento;


	private TabelaItensComposicao tabelaItensComposicao;

	@Before
	public void setUp() {
		this.tabelaItensOrcamento = new TabelaItensOrcamento("1");
		this.tabelaItensComposicao = new TabelaItensComposicao("1");
	}



	@Test
	public void inserirUmInsumoNaTabelaDeItensDaComposicao() {

		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setTipo(TipoInsumo.MATERIAL);

		tabelaItensComposicao.adicionarItem(insumo, BigDecimal.TEN);


		assertEquals(1, tabelaItensComposicao.getItensInsumo().size());

	}

	@Test
	public void inserirUmaComposicaoNaTabelaDeItensDoOrcamento() {

		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setTipo(TipoInsumo.MATERIAL);

		tabelaItensComposicao.adicionarItem(insumo, BigDecimal.TEN);

		for(ItemComposicaoInsumo ici : tabelaItensComposicao.getItensInsumo()) {
			tabelaItensOrcamento.adicionarItem(ici.getComposicaoServico(), BigDecimal.TEN);
		}


		assertEquals(1, tabelaItensOrcamento.getItensOrcamento().size());

	}


	@Test
	public void inserirTresComposicoesNaTabelaDeItensDoOrcamento() {

		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setTipo(TipoInsumo.MATERIAL);

		Insumo insumo2 = new Insumo();
		insumo2.setId(2L);
		insumo2.setNome("CIMENTO CP3A");
		insumo2.setTipo(TipoInsumo.MATERIAL);

		Insumo insumo3 = new Insumo();
		insumo3.setId(3L);
		insumo3.setNome("TIJOLO 8 FURO");
		insumo3.setTipo(TipoInsumo.MATERIAL);



		ItemComposicaoInsumo ici = new ItemComposicaoInsumo();
	    ici.setId(1L);
		ici.setInsumo(insumo);
		ici.setTipoInsumo(TipoInsumo.MATERIAL);
		ici.setValorCoeficiente(BigDecimal.TEN);

		ItemComposicaoInsumo ici2 = new ItemComposicaoInsumo();
	    ici.setId(2L);
		ici.setInsumo(insumo2);
		ici.setTipoInsumo(TipoInsumo.MATERIAL);
		ici.setValorCoeficiente(BigDecimal.TEN);

		ItemComposicaoInsumo ici3 = new ItemComposicaoInsumo();
	    ici.setId(3L);
		ici.setInsumo(insumo3);
		ici.setTipoInsumo(TipoInsumo.MATERIAL);
		ici.setValorCoeficiente(BigDecimal.TEN);

		Set<ItemComposicaoInsumo> conjuntoComposicoesInsumo = new HashSet<>();

		conjuntoComposicoesInsumo.add(ici);
		conjuntoComposicoesInsumo.add(ici2);
		conjuntoComposicoesInsumo.add(ici3);


		ComposicaoServico cs = new ComposicaoServico();
		cs.setId(1L);
		cs.adicionarItens(conjuntoComposicoesInsumo);


		System.out.println(tabelaItensComposicao.getItensInsumo().size());

		tabelaItensOrcamento.adicionarItem(cs, BigDecimal.ONE);


		assertEquals(1, tabelaItensOrcamento.getItensOrcamento().size());

	}








	public void detalharOrcamentoComTresComposicoesComUmInsumoEmCadaComposicao() {

		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setUnidade(EnumUnidade.M3);
		insumo.setTipo(TipoInsumo.MATERIAL);

		Insumo insumo2 = new Insumo();
		insumo2.setId(2L);
		insumo2.setNome("CIMENTO CP3A");
		insumo2.setUnidade(EnumUnidade.UNITARIO);
		insumo2.setTipo(TipoInsumo.MATERIAL);

		Insumo insumo3 = new Insumo();
		insumo3.setId(3L);
		insumo3.setNome("TIJOLO 8 FURO");
		insumo3.setUnidade(EnumUnidade.UNITARIO);;
		insumo3.setTipo(TipoInsumo.MATERIAL);



		ItemComposicaoInsumo ici = new ItemComposicaoInsumo();
	    ici.setId(1L);
		ici.setInsumo(insumo);
		ici.setTipoInsumo(TipoInsumo.MATERIAL);
		ici.setValorCoeficiente(BigDecimal.TEN);

		ItemComposicaoInsumo ici2 = new ItemComposicaoInsumo();
	    ici2.setId(2L);
		ici2.setInsumo(insumo2);
		ici2.setTipoInsumo(TipoInsumo.MATERIAL);
		ici2.setValorCoeficiente(BigDecimal.TEN);

		ItemComposicaoInsumo ici3 = new ItemComposicaoInsumo();
	    ici3.setId(3L);
		ici3.setInsumo(insumo3);
		ici3.setTipoInsumo(TipoInsumo.MATERIAL);
		ici3.setValorCoeficiente(BigDecimal.TEN);

		Set<ItemComposicaoInsumo> conjuntoComposicoesInsumo = new HashSet<>();

		conjuntoComposicoesInsumo.add(ici);
		conjuntoComposicoesInsumo.add(ici2);
		conjuntoComposicoesInsumo.add(ici3);


		ComposicaoServico cs = new ComposicaoServico();
		cs.setId(1L);
		cs.adicionarItens(conjuntoComposicoesInsumo);


		System.out.println(tabelaItensComposicao.getItensInsumo().size());

		tabelaItensOrcamento.adicionarItem(cs, BigDecimal.ONE);



		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Mato Grosso do Sul");
		estado.setSigla("MS");

		List<ItemOrcamento> itensOrcamento = tabelaItensOrcamento.getItensOrcamento();

		List<DetalheMaterialBO> materiais = new DetalheMaterialService().listarMateriaisDoOrcamento(itensOrcamento, estado);


		System.out.println(materiais);


		assertEquals(1, tabelaItensOrcamento.getItensOrcamento().size());

	}




	public void detalharOrcamentoComUmaComposicaoQueTemUmaComposicaoQueTemUmInsumo() {

		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("MANGUEIRA DE 8M");
		insumo.setUnidade(EnumUnidade.M3);
		insumo.setTipo(TipoInsumo.MATERIAL);


		ItemComposicaoInsumo ici = new ItemComposicaoInsumo();
	    ici.setId(1L);
		ici.setInsumo(insumo);
		ici.setTipoInsumo(TipoInsumo.MATERIAL);
		ici.setValorCoeficiente(BigDecimal.TEN);

		Set<ItemComposicaoInsumo> conjuntoComposicoesInsumo = new HashSet<>();
		conjuntoComposicoesInsumo.add(ici);


		ComposicaoServico composicaoComUmInsumo = new ComposicaoServico();
		composicaoComUmInsumo.setId(1L);
		composicaoComUmInsumo.adicionarItens(conjuntoComposicoesInsumo);


		ComposicaoServico composicaoComComposicao = new ComposicaoServico();
		composicaoComComposicao.setId(2L);

		ItemComposicaoInsumo icc = new ItemComposicaoInsumo();
		icc.setId(1L);
		icc.setValorCoeficiente(BigDecimal.ONE);
		icc.setComposicaoDaComposicao(composicaoComComposicao);
		icc.setComposicaoServico(composicaoComUmInsumo);


		Set<ItemComposicaoInsumo> composicoesComComposicao = new HashSet<>();
		composicoesComComposicao.add(icc);

		composicaoComComposicao.adicionarItens(composicoesComComposicao);


		Estado estado = new Estado();
		estado.setId(1L);
		estado.setNome("Mato Grosso do Sul");
		estado.setSigla("MS");

		tabelaItensOrcamento.adicionarItem(composicaoComComposicao, BigDecimal.ONE);

		List<ItemOrcamento> itensOrcamento = tabelaItensOrcamento.getItensOrcamento();

		List<DetalheMaterialBO> materiais = new DetalheMaterialService().listarMateriaisDoOrcamento(itensOrcamento, estado);


		System.out.println(materiais);


		assertEquals(1, tabelaItensOrcamento.getItensOrcamento().size());

	}











    public void detalharOrcamentoVerificaQuantidadeDeUmDeterminadoMaterial() {

        Insumo insumo = new Insumo();
        insumo.setId(1L);
        insumo.setNome("AREIA FINA");
        insumo.setUnidade(EnumUnidade.M3);
        insumo.setTipo(TipoInsumo.MATERIAL);

        Insumo insumo2 = new Insumo();
        insumo2.setId(2L);
        insumo2.setNome("CIMENTO CP3A");
        insumo2.setUnidade(EnumUnidade.UNITARIO);
        insumo2.setTipo(TipoInsumo.MATERIAL);

        Insumo insumo3 = new Insumo();
        insumo3.setId(3L);
        insumo3.setNome("TIJOLO 8 FURO");
        insumo3.setUnidade(EnumUnidade.UNITARIO);;
        insumo3.setTipo(TipoInsumo.MATERIAL);



        ItemComposicaoInsumo ici = new ItemComposicaoInsumo();
        ici.setId(1L);
        ici.setInsumo(insumo);
        ici.setTipoInsumo(TipoInsumo.MATERIAL);
        ici.setValorCoeficiente(BigDecimal.TEN);

        ItemComposicaoInsumo ici2 = new ItemComposicaoInsumo();
        ici2.setId(2L);
        ici2.setInsumo(insumo);
        ici2.setTipoInsumo(TipoInsumo.MATERIAL);
        ici2.setValorCoeficiente(BigDecimal.ONE);

        ItemComposicaoInsumo ici3 = new ItemComposicaoInsumo();
        ici3.setId(3L);
        ici3.setInsumo(insumo3);
        ici3.setTipoInsumo(TipoInsumo.MATERIAL);
        ici3.setValorCoeficiente(BigDecimal.ZERO);



        ItemComposicaoInsumo ici4 = new ItemComposicaoInsumo();
        ici4.setId(4L);
        ici4.setInsumo(insumo);
        ici4.setTipoInsumo(TipoInsumo.MATERIAL);
        ici4.setValorCoeficiente(BigDecimal.TEN);

        ItemComposicaoInsumo ici5 = new ItemComposicaoInsumo();
        ici5.setId(5L);
        ici5.setInsumo(insumo2);
        ici5.setTipoInsumo(TipoInsumo.MATERIAL);
        ici5.setValorCoeficiente(BigDecimal.ONE);

        ItemComposicaoInsumo ici6 = new ItemComposicaoInsumo();
        ici6.setId(6L);
        ici6.setInsumo(insumo3);
        ici6.setTipoInsumo(TipoInsumo.MATERIAL);
        ici6.setValorCoeficiente(BigDecimal.ZERO);


        Set<ItemComposicaoInsumo> conjuntoComposicoesInsumo = new HashSet<>();

        conjuntoComposicoesInsumo.add(ici);
        conjuntoComposicoesInsumo.add(ici2);
        conjuntoComposicoesInsumo.add(ici3);


        ComposicaoServico cs = new ComposicaoServico();
        cs.setId(1L);
        cs.adicionarItens(conjuntoComposicoesInsumo);



        Set<ItemComposicaoInsumo> conjuntoComposicoesInsumo2 = new HashSet<>();

        conjuntoComposicoesInsumo2.add(ici2);
        conjuntoComposicoesInsumo2.add(ici5);
        conjuntoComposicoesInsumo2.add(ici6);


        ComposicaoServico cs1 = new ComposicaoServico();
        cs1.setId(2L);
        cs1.adicionarItens(conjuntoComposicoesInsumo2);



        System.out.println(tabelaItensComposicao.getItensInsumo().size());

        tabelaItensOrcamento.adicionarItem(cs, BigDecimal.ONE);
        tabelaItensOrcamento.adicionarItem(cs1, BigDecimal.ONE);



        Estado estado = new Estado();
        estado.setId(1L);
        estado.setNome("Mato Grosso do Sul");
        estado.setSigla("MS");

        List<ItemOrcamento> itensOrcamento = tabelaItensOrcamento.getItensOrcamento();

        List<DetalheMaterialBO> materiais = new DetalheMaterialService().listarMateriaisDoOrcamento(itensOrcamento, estado);


        System.out.println(materiais);


        assertEquals(2, tabelaItensOrcamento.getItensOrcamento().size());

        for (DetalheMaterialBO det : materiais) {

        	if(det.getId().equals(insumo.getId())) {

                assertEquals(new BigDecimal("20.00"), det.getQuantidade());

                break;
            }
        }


    }


	private List<Insumo> criarInsumos() {

		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setUnidade(EnumUnidade.M3);
		insumo.setTipo(TipoInsumo.MATERIAL);

		Insumo insumo2 = new Insumo();
		insumo2.setId(2L);
		insumo2.setNome("CIMENTO CP3A");
		insumo2.setUnidade(EnumUnidade.UNITARIO);
		insumo2.setTipo(TipoInsumo.MATERIAL);

		Insumo insumo3 = new Insumo();
		insumo3.setId(3L);
		insumo3.setNome("TIJOLO 8 FURO");
		insumo3.setUnidade(EnumUnidade.UNITARIO);;
		insumo3.setTipo(TipoInsumo.MATERIAL);

		return Arrays.asList(insumo, insumo2, insumo3);


	}



}
