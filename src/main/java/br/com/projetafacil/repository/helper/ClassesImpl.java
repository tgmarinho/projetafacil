package br.com.projetafacil.repository.helper;

import br.com.projetafacil.model.Classe;
import br.com.projetafacil.paginacao.PaginacaoUtil;
import br.com.projetafacil.repository.filter.ClasseFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ClassesImpl implements ClassesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Classe> filtrar(ClasseFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Classe.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(ClasseFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Classe.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ClasseFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (!StringUtils.isEmpty(filtro.getSiglaENome())) {
				Criterion nome = Restrictions.ilike("nome", filtro.getSiglaENome(), MatchMode.ANYWHERE);
				Criterion codigo = Restrictions.ilike("sigla", filtro.getSiglaENome(), MatchMode.ANYWHERE);
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(nome);
				disjunction.add(codigo);
				criteria.add(disjunction);
			}
		}
	}

}
