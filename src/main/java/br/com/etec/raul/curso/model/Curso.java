package br.com.etec.raul.curso.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcurso;

    private String nomecurso;

    @OneToMany(mappedBy = "curso")
    private List<Aluno> alunoscurso = new ArrayList<>();

    public List<Aluno> getAlunoscurso() {
        return alunoscurso;
    }

    public void setAlunoscurso(List<Aluno> alunoscurso) {
        this.alunoscurso = alunoscurso;
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(idcurso, curso.idcurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcurso);
    }
}
