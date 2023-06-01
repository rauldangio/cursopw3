package br.com.etec.raul.curso.repository;

import br.com.etec.raul.curso.model.Cidade;
import br.com.etec.raul.curso.repository.filter.cidade.CidadeRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>, CidadeRepositoryQuery {

}
