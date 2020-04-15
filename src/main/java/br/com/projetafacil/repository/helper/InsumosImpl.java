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

import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.paginacao.PaginacaoUtil;
import br.com.projetafacil.repository.filter.InsumoFilter;

public class InsumosImpl implements InsumosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Insumo> filtrar(InsumoFilter filtro, Pageable pageable) {
	Criteria criteria = manager.unwrap(Session.class).createCriteria(Insumo.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(InsumoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Insumo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(InsumoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getNome())) {
				Criterion nome = Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE);
				criteria.add(nome);
			}
		}
	}



	

}
