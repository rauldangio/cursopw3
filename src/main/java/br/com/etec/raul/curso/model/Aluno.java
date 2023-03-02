package br.com.etec.raul.curso.model;


import javax.persistence.*;

@Entity
@Table(name = "aluno")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeAluno;
    
    private int idCurso;
    private int idCidade;


}
