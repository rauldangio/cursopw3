package br.com.etec.raul.curso.repository.filter;

import br.com.etec.raul.curso.model.Curso;

import java.util.List;

public class CursoFilter {
    private String nomecurso;

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    private List<Curso> filtrarCursoPorNome(){
        return null;
    }
}
