package br.com.etec.raul.curso.controller;


import br.com.etec.raul.curso.model.Curso;
import br.com.etec.raul.curso.repository.CursoRepository;
import br.com.etec.raul.curso.repository.filter.CursoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    public CursoRepository cursoRepository;

    @GetMapping()
    public Page<Curso> pesquisar(CursoFilter cursoFilter, Pageable pageable)
    {
        return cursoRepository.filtrar(cursoFilter, pageable);
    }

    @GetMapping("/todos")
    public List<Curso> listarTodosCursos(){
        return  cursoRepository.findAll();
    }

}
