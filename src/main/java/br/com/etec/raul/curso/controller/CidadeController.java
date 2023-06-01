package br.com.etec.raul.curso.controller;

import br.com.etec.raul.curso.model.Cidade;
import br.com.etec.raul.curso.repository.CidadeRepository;
import br.com.etec.raul.curso.repository.filter.CidadeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;


    @GetMapping()
    private Page<Cidade> query(CidadeFilter cidadeFilter, Pageable pageable)
    {
        return cidadeRepository.filtrar(cidadeFilter, pageable);
    }




    @GetMapping("todos")
    private List<Cidade> listarTodasCidades(){
        return cidadeRepository.findAll();
    }



}
