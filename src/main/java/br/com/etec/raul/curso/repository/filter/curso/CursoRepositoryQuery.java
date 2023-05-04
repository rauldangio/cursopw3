package br.com.etec.raul.curso.repository.filter.curso;

import br.com.etec.raul.curso.model.Curso;
import br.com.etec.raul.curso.repository.filter.CursoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CursoRepositoryQuery {

    public Page<Curso> filtrar(CursoFilter cursoFilter, Pageable pageable);

}
