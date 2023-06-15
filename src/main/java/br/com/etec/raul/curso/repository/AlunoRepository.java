package br.com.etec.raul.curso.repository;

import br.com.etec.raul.curso.model.Aluno;
import br.com.etec.raul.curso.repository.filter.aluno.AlunoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, AlunoRepositoryQuery {

}
