package br.com.etec.raul.curso.repository.filter.aluno;

import br.com.etec.raul.curso.repository.filter.AlunoFilter;
import br.com.etec.raul.curso.repository.projections.AlunoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlunoRepositoryQuery {

    public Page<AlunoDTO> filtrar(AlunoFilter alunoFilter, Pageable pageable);

}
