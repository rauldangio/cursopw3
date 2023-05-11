package br.com.etec.raul.curso.repository.filter.curso;

import br.com.etec.raul.curso.model.Curso;
import br.com.etec.raul.curso.repository.filter.CursoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
        return null;
    }

    private Predicate[] criarWhere(CursoFilter cursoFilter, CriteriaBuilder builder, Root<Curso> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils)
    }
}
