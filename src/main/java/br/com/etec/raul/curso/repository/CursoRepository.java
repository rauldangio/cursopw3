package br.com.etec.raul.curso.repository;

import br.com.etec.raul.curso.model.Curso;
import br.com.etec.raul.curso.repository.filter.curso.CursoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>, CursoRepositoryQuery {
}
