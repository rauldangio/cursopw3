package br.com.etec.raul.curso.repository.filter.cidade;

import br.com.etec.raul.curso.model.Cidade;
import br.com.etec.raul.curso.repository.AlunoRepository;
import br.com.etec.raul.curso.repository.filter.CidadeFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CidadeRepositoryQuery {
    public Page<Cidade> filtrar(CidadeFilter  cidadeFilter, Pageable pageable);
}
