package br.com.projetafacil.repository.helper;

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

import br.com.projetafacil.model.Orcamento;
import br.com.projetafacil.paginacao.PaginacaoUtil;
import br.com.projetafacil.repository.filter.OrcamentoFilter;

public class OrcamentosImpl implements OrcamentosQueries {
		
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	


	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Orcamento> filtrar(OrcamentoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Orcamento.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(OrcamentoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Orcamento.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(OrcamentoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getDescricao())) {
				Criterion descricao = Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE);
				criteria.add(descricao);
			}
			if (!StringUtils.isEmpty(filtro.getUsuario())) {
				Criterion usuario = Restrictions.eq("usuario", filtro.getUsuario());
				criteria.add(usuario);
			}
		}
	}



	@Override
	@Transactional(readOnly = true)
	public Orcamento buscarComItens(Long id) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Orcamento.class);
		criteria.createAlias("itensOrcamento", "item", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Orcamento) criteria.uniqueResult();
	}


}
