package br.com.etec.raul.curso.repository.filter.cidade;

import br.com.etec.raul.curso.model.Cidade;
import br.com.etec.raul.curso.repository.filter.CidadeFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Predicates;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class CidadeRepositoryImpl implements CidadeRepositoryQuery{
    @Autowired
    private EntityManager manager;

    @Override
    public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Cidade> criteria = builder.createQuery(Cidade.class);
        Root<Cidade> root = criteria.from(Cidade.class);

        Predicate[] predicates = criarRestricoes(builder, cidadeFilter, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("uf")));

        TypedQuery<Cidade> query = manager.createQuery(criteria);
        adicionarRestricoesDaPaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(),pageable, total(cidadeFilter));
    }

    private Long total(CidadeFilter cidadeFilter)
    {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Cidade> root = criteria.from(Cidade.class);

        Predicate[] predicates = criarRestricoes(builder, cidadeFilter, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("uf")));
        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }



    private void adicionarRestricoesDaPaginacao(TypedQuery<Cidade> query, Pageable pageable)
    {
        int paginaatual = pageable.getPageNumber();
        int totalRegistroPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalRegistroPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistroPorPagina);
    }

    private Predicate[] criarRestricoes(CriteriaBuilder builder, CidadeFilter cidadeFilter, Root<Cidade> root)
    {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(cidadeFilter.getNomecidade()))
        {
            predicates.add(builder.like(builder.lower(root.get("nomecidade")),
            "%" + cidadeFilter.getNomecidade().toLowerCase() + "%"
            ));
        }

        if (!StringUtils.isEmpty(cidadeFilter.getUf()))
        {
            predicates.add(builder.like(builder.lower(root.get("uf")),
                    "%" + cidadeFilter.getUf().toLowerCase() + "%"
            ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
