package br.com.etec.raul.curso.controller;


import br.com.etec.raul.curso.model.Curso;
import br.com.etec.raul.curso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    public CursoRepository cursoRepository;

    @GetMapping("/todos")
    public List<Curso> listarTodosCursos(){
        return  cursoRepository.findAll();
    }

}
