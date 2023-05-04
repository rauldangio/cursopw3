package br.com.etec.raul.curso.controller;

import br.com.etec.raul.curso.model.Cidade;
import br.com.etec.raul.curso.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping("todos")
    private List<Cidade> listarTodasCidades(){
        return cidadeRepository.findAll();
    }



}
