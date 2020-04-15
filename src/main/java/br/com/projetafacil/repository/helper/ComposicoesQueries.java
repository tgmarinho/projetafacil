package br.com.projetafacil.repository.helper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.projetafacil.dto.ComposicaoServicoDTO;
import br.com.projetafacil.dto.InsumoDTO;
import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.Usuario;
import br.com.projetafacil.repository.filter.ComposicaoServicoFilter;

public interface ComposicoesQueries {

	Page<ComposicaoServico> filtrar(ComposicaoServicoFilter filtro, Pageable pageable);
	
	ComposicaoServico buscarComItens(Long id);

	List<InsumoDTO> buscarInsumoPorNomeOuCodigoBIM(String nomeOuCodigoBim, String tipoInsumo);

	List<ComposicaoServicoDTO> buscarServicoPorNomeOuCodigoBIM(String nomeOuCodigoBim, Usuario usuario);
	
}
