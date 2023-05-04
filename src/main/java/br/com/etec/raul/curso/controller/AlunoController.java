package br.com.etec.raul.curso.controller;

import br.com.etec.raul.curso.model.Aluno;
import br.com.etec.raul.curso.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("todos")
    public List<Aluno> listarTodosAlunos(){
        return alunoRepository.findAll();
    }
}
