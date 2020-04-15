package br.com.projetafacil.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.projetafacil.dto.ComposicaoServicoDTO;
import br.com.projetafacil.dto.InsumoDTO;
import br.com.projetafacil.model.ComposicaoServico;
import br.com.projetafacil.model.Usuario;
import br.com.projetafacil.model.enums.TipoUsuario;
import br.com.projetafacil.paginacao.PaginacaoUtil;
import br.com.projetafacil.repository.filter.ComposicaoServicoFilter;

public class ComposicoesImpl implements ComposicoesQueries {
		
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	

	@Override
	public List<InsumoDTO> buscarInsumoPorNomeOuCodigoBIM(String nomeOuCodigoBim, String tipoInsumo) {
		
		StringBuilder jpql = new StringBuilder("SELECT DISTINCT NEW br.com.projetafacil.dto.InsumoDTO(insumo.id, insumo.nome, insumo.codigoBIM) FROM ");
		jpql.append(tipoInsumo);
		jpql.append(" insumo WHERE lower(insumo.nome) LIKE lower(?1) OR lower(insumo.codigoBIM) LIKE lower(?1) ");
		
		List<InsumoDTO> insumosFiltrados = manager.createQuery(jpql.toString(), InsumoDTO.class)
				.setParameter(1, "%" + nomeOuCodigoBim + "%")
				.getResultList();
		return insumosFiltrados;
	}

	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<ComposicaoServico> filtrar(ComposicaoServicoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ComposicaoServico.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(ComposicaoServicoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ComposicaoServico.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ComposicaoServicoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getServico())) {
				Criterion nome = Restrictions.ilike("nome", filtro.getServico(), MatchMode.ANYWHERE);
				criteria.add(nome);
			}
			if (!StringUtils.isEmpty(filtro.getUsuario())) {
				Criterion usuario = Restrictions.eq("usuario.id", filtro.getUsuario().getId());
//				criteria.createAlias("insumos", "ins", JoinType.LEFT_OUTER_JOIN);
//				criteria.createAlias("composicoes", "compo", JoinType.LEFT_OUTER_JOIN);
				criteria.createAlias("usuario", "usuario", JoinType.LEFT_OUTER_JOIN);
				Criterion admin = Restrictions.eq("usuario.tipoUsuario", TipoUsuario.ADMINISTRADOR);
				criteria.add(Restrictions.disjunction(usuario, admin));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				
//				Criterion usuario = Restrictions.or(Restrictions.eq("usuario", filtro.getUsuario())).add(Restrictions.eq("usuario.tipoUsuario", TipoUsuario.ADMINISTRADOR));
//				criteria.add(usuario);
			}
		}
	}

	

	@Override
	@Transactional(readOnly = true)
	public ComposicaoServico buscarComItens(Long id) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(ComposicaoServico.class);
		criteria.createAlias("insumos", "ins", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("classe", "classe", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (ComposicaoServico) criteria.uniqueResult();
	}



	@Override
	public List<ComposicaoServicoDTO> buscarServicoPorNomeOuCodigoBIM(String nomeOuCodigoBim, Usuario usuario) {
		
		StringBuilder jpql = new StringBuilder("select distinct new br.com.projetafacil.dto.ComposicaoServicoDTO(cs.id, cs.nome, cs.referencia, cs.unidade) ");
		jpql.append(" FROM ComposicaoServico cs JOIN cs.usuario usuario ");
		jpql.append(" WHERE upper(cs.nome) LIKE lower(?1) AND (cs.usuario.id = ?2 OR cs.usuario.tipoUsuario = ?3 )");
		
		List<ComposicaoServicoDTO> composicoesFiltrados = manager.createQuery(jpql.toString(), ComposicaoServicoDTO.class)
				.setParameter(1, "%" + nomeOuCodigoBim.toUpperCase() + "%")
				.setParameter(2, usuario.getId())
				.setParameter(3, TipoUsuario.ADMINISTRADOR)
				.getResultList();
		return composicoesFiltrados;
	}

}
