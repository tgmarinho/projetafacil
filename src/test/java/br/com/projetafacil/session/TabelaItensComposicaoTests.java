package br.com.projetafacil.session;

import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.model.ItemComposicaoInsumo;
import br.com.projetafacil.model.enums.TipoInsumo;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TabelaItensComposicaoTests {

	
	private TabelaItensComposicao tabelaItensComposicao;
	
	
	@Before
	public void setUp() {
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
	public void inserirTresInsumosMateriaisNaTabelaDeItensDaComposicao() {
		
		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setTipo(TipoInsumo.MATERIAL);
		
		Insumo insumo2 = new Insumo();
		insumo2.setId(2L);
		insumo2.setNome("CIMENTO CP3");
		insumo2.setTipo(TipoInsumo.MATERIAL);
		
		Insumo insumo3 = new Insumo();
		insumo3.setId(3L);
		insumo3.setNome("TIJOLO CP4");
		insumo3.setTipo(TipoInsumo.MATERIAL);
		
		
		tabelaItensComposicao.adicionarItem(insumo, BigDecimal.TEN);
		tabelaItensComposicao.adicionarItem(insumo2, BigDecimal.ONE);
		tabelaItensComposicao.adicionarItem(insumo3, BigDecimal.TEN);
		
		assertEquals(3, tabelaItensComposicao.getItensInsumo().size());
		
	}
	
	
	@Test 
	public void inserirUmaComposicaoComUmMaterialNaTabelaDeItensDaComposicao() {
		
		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA COM PEDRA BRITA");
		insumo.setTipo(TipoInsumo.MATERIAL);
		
		ComposicaoServico composicao = new ComposicaoServico();
		composicao.setId(1L);
		composicao.setNome("AREIA COM PEDRA");
		
		ItemComposicaoInsumo ici = new ItemComposicaoInsumo();
		ici.setId(1L);
		ici.setTipoInsumo(TipoInsumo.MATERIAL);
		ici.setInsumo(insumo);
		ici.setComposicaoServico(composicao);
		
		composicao.adicionaItem(ici);
		
		
		tabelaItensComposicao.adicionarItemComposicao(composicao, BigDecimal.ONE);
		
		assertEquals(1, tabelaItensComposicao.getItensInsumo().size());
		
	}
	
	
	@Test 
	public void inserirUmInsumoMaterialEUmaComposicaoNaTabelaDeItensDaComposicao() {
		
		
		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA COM PEDRA BRITA");
		insumo.setTipo(TipoInsumo.MATERIAL);
		
		ComposicaoServico composicao = new ComposicaoServico();
		composicao.setId(1L);
		composicao.setNome("AREIA COM PEDRA");
		
		ItemComposicaoInsumo ici = new ItemComposicaoInsumo();
		ici.setId(1L);
		ici.setTipoInsumo(TipoInsumo.MATERIAL);
		ici.setInsumo(insumo);
		ici.setComposicaoServico(composicao);
		
		composicao.adicionaItem(ici);
		
		
		Insumo insumo2 = new Insumo();
		insumo2.setId(1L);
		insumo2.setNome("AREIA FINA");
		insumo2.setTipo(TipoInsumo.MATERIAL);
		
		tabelaItensComposicao.adicionarItem(insumo2, BigDecimal.TEN);
		tabelaItensComposicao.adicionarItemComposicao(composicao, BigDecimal.TEN);
		
		System.out.println(tabelaItensComposicao.getItensInsumo());
		
		
		assertEquals(2, tabelaItensComposicao.getItensInsumo().size());
	
	}
	
	@Test 
	public void excluirUmItemComposicaoDeTres() {
		
		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setTipo(TipoInsumo.MATERIAL);
		
		Insumo insumo2 = new Insumo();
		insumo2.setId(2L);
		insumo2.setNome("CIMENTO CP3");
		insumo2.setTipo(TipoInsumo.MATERIAL);
		
		Insumo insumo3 = new Insumo();
		insumo3.setId(3L);
		insumo3.setNome("TIJOLO CP4");
		insumo3.setTipo(TipoInsumo.MATERIAL);
		
		
		tabelaItensComposicao.adicionarItem(insumo, BigDecimal.TEN);
		tabelaItensComposicao.adicionarItem(insumo2, BigDecimal.ONE);
		tabelaItensComposicao.adicionarItem(insumo3, BigDecimal.TEN);
		
		assertEquals(3, tabelaItensComposicao.getItensInsumo().size());
		
		tabelaItensComposicao.excluirItem(insumo3);
		
		
		assertEquals(2, tabelaItensComposicao.getItensInsumo().size());
		
		
	}
	@Test
	public void alterarQuantidadeItemMaterial() {
		
		Insumo insumo = new Insumo();
		insumo.setId(1L);
		insumo.setNome("AREIA FINA");
		insumo.setTipo(TipoInsumo.MATERIAL);
		
		Insumo insumo2 = new Insumo();
		insumo2.setId(2L);
		insumo2.setNome("CIMENTO CP3");
		insumo2.setTipo(TipoInsumo.MATERIAL);
		
		
		tabelaItensComposicao.adicionarItem(insumo, BigDecimal.ONE);
		tabelaItensComposicao.adicionarItem(insumo2, BigDecimal.ONE);
		
		
		tabelaItensComposicao.alterarCoeficienteItem(insumo, BigDecimal.TEN);
		tabelaItensComposicao.alterarCoeficienteItem(insumo2, BigDecimal.TEN);

		BigDecimal vinte  = new BigDecimal("20");

		assertEquals(vinte, tabelaItensComposicao.getCoeficienteTotal());
		
	}
	
	
	
}
