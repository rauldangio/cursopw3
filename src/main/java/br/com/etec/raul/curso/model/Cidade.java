package br.com.etec.raul.curso.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cidade")

public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomecidade;
    private String uf;

    @OneToMany(mappedBy = "cidade")
    @JsonIgnore
    private List<Aluno> alunoscidade = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomecidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomecidade = nomeCidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Aluno> getAlunoscidade() {
        return alunoscidade;
    }

    public void setAlunoscidade(List<Aluno> alunoscidade) {
        this.alunoscidade = alunoscidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return id == cidade.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
