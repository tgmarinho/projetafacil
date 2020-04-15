package br.com.projetafacil.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.repository.Composicoes;
import br.com.projetafacil.util.CodificacaoUtils;

@Service
public class ComposicaoServicoService {

	@Autowired
	private Composicoes composicoes;
	
	@Transactional
	public ComposicaoServico salvar(ComposicaoServico composicaoServico) {

		if (composicaoServico.isNova()) {
			String ultimoServicoDaSubEtapa = composicoes.findByServicoPorClasseEUsuario(
												composicaoServico.getClasse().getId(), composicaoServico.getUsuario().getId());
			String novoCodigoServico = CodificacaoUtils.obterNovoCodigoParaServico(ultimoServicoDaSubEtapa);
			composicaoServico.setCodigo(novoCodigoServico);
		}

		composicaoServico.setNome(composicaoServico.getNome().toUpperCase());

		return composicoes.saveAndFlush(composicaoServico);
	}

	@Transactional
	public void excluir(Long id) {
		try {

			ComposicaoServico composicaoServico = composicoes.findOne(id);

//			composicaoServico.getMateriais().clear();
//			composicaoServico.getMaoDeObras().clear();
//			composicaoServico.getEquipamentos().clear();
			
			composicoes.saveAndFlush(composicaoServico);

			composicoes.delete(id);

		} catch (Exception err) {
			err.printStackTrace();
			throw new RuntimeException(
					"Ops! Não foi possível deletar a Composição devido a um relacionamento com outra composição ou orçamento.");
		}
	}

//	public List<ComposicaoServico> servicosDaSubEtapaSelecionada(Long id) {
//		return composicoes.findBySubEtapaId(id);
//	}
//
//	public ComposicaoServico findOne(Long id) {
//		return composicoes.findOne(id);
//	}
	
	public List<ComposicaoServico> filtrar(ComposicaoServico filtro) {
		return composicoes.findAll();
	}

//	public ComposicaoServico buscarComposicaoComItemsNoLazy(Long id) {
//		return composicoes.buscarComposicaoComItemsNoLazy(id);
//	}
	
}
