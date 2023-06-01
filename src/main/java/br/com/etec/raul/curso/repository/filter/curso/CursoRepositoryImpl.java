package br.com.etec.raul.curso.repository.filter.curso;

import br.com.etec.raul.curso.model.Curso;
import br.com.etec.raul.curso.repository.filter.CursoFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CursoRepositoryImpl implements CursoRepositoryQuery{

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Curso> filtrar(CursoFilter cursoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Curso> criteria = builder.createQuery(Curso.class);
        Root<Curso> root = criteria.from(Curso.class);

        Predicate[] predicate = criarWhere(cursoFilter, builder, root );
        criteria.where(predicate);
        criteria.orderBy(builder.asc(root.get("nomecurso")));

        TypedQuery<Curso> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);
        return new PageImpl<>(query.getResultList(), pageable, total(cursoFilter));
    }

    private Long total(CursoFilter cursoFilter)
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Curso> root = criteria.from(Curso.class);

        Predicate[] predicate = criarWhere(cursoFilter, builder, root );
        criteria.where(predicate);
        criteria.orderBy(builder.asc(root.get("nomecurso")));
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePaginacao(TypedQuery<Curso> query, Pageable pageable) {
        int paginaatual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalRegistroPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }



    private Predicate[] criarWhere(CursoFilter cursoFilter, CriteriaBuilder builder, Root<Curso> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(cursoFilter.getNomecurso()))
        {
            predicates.add(builder.like(builder.lower(root.get("nomecurso")),
                    "%" + cursoFilter.getNomecurso().toLowerCase() + "%"
                    ));
        }



        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
