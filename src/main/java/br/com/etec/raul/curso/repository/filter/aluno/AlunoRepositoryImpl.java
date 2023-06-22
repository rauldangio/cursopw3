package br.com.etec.raul.curso.repository.filter.aluno;

import br.com.etec.raul.curso.model.Aluno;
import br.com.etec.raul.curso.model.Curso;
import br.com.etec.raul.curso.repository.filter.AlunoFilter;
import br.com.etec.raul.curso.repository.filter.CursoFilter;
import br.com.etec.raul.curso.repository.projections.AlunoDTO;
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

public class AlunoRepositoryImpl implements AlunoRepositoryQuery{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<AlunoDTO> filtrar(AlunoFilter alunoFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<AlunoDTO> criteria = builder.createQuery(AlunoDTO.class);
        Root<Aluno> root = criteria.from(Aluno.class);

        criteria.select(builder.construct(AlunoDTO.class,
                root.get("id"),
                root.get("nomealuno"),
                root.get("cidade").get("nomecidade"),
                root.get("cidade").get("uf"),
                root.get("curso").get("nomecurso")
                ));


        Predicate[] predicates = criarRestricoes(builder, root, alunoFilter);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomealuno")));

        TypedQuery<AlunoDTO> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(alunoFilter));

    }

    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaatual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalRegistroPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Long total(AlunoFilter alunoFilter)
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Aluno> root = criteria.from(Aluno.class);

        Predicate[] predicates = criarRestricoes(builder, root, alunoFilter);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomealuno")));
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }

    private Predicate[] criarRestricoes(CriteriaBuilder builder, Root<Aluno> root, AlunoFilter alunoFilter) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(alunoFilter.getNomealuno()))
        {
            predicates.add(builder.like(builder.lower(root.get("nomealuno")),
                    "%" + alunoFilter.getNomealuno().toLowerCase() + "%"
            ));
        }

        if (!StringUtils.isEmpty(alunoFilter.getNomecidade()))
        {
            predicates.add(builder.like(builder.lower(root.get("cidade").get("nomecidade")),
                    "%" + alunoFilter.getNomecidade().toLowerCase() + "%"
            ));
        }
        if (!StringUtils.isEmpty(alunoFilter.getNomecurso()))
        {
            predicates.add(builder.like(builder.lower(root.get("curso").get("nomecurso")),
                    "%" + alunoFilter.getNomecurso().toLowerCase() + "%"
            ));
        }
        if (!StringUtils.isEmpty(alunoFilter.getNomecidade()))
        {
            predicates.add(builder.equal(builder.lower(root.get("cidade").get("uf")),
                     alunoFilter.getUf().toLowerCase()
            ));
        }
        /*
        // datas maiores que a inserida
        if (alunoFilter.getData() != null)
        {
            predicates.add(builder.greaterThanOrEqualTo(builder.lower(root.get("data")),
                     alunoFilter.getNomecurso()
            ));
        }

        // data menores que a sua
        if (alunoFilter.getData() != null)
        {
            predicates.add(builder.lessThanOrEqualTo(builder.lower(root.get("data")),
                     alunoFilter.getNomecurso()
            ));
        }
        */




        return predicates.toArray(new Predicate[predicates.size()]);

    }
}
