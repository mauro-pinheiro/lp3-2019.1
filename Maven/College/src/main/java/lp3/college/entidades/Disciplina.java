package lp3.college.entidades;

import java.util.HashSet;
import java.util.Set;

public class Disciplina implements EntidadeNomeada{
    private int id;
    private String codigo;
    private String nome;
    private String ementa;
    private String cargaHoraria;
    private Curso curso;
    private Set<Professor> ministrantes = new HashSet<>();
    private Set<Aluno> matriculados = new HashSet<>();

    public Disciplina(String codigo, String nome, String ementa, String ch){
        this.codigo = codigo;
        this.nome = nome;
        this.ementa = ementa;
        this.cargaHoraria = ch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }


    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<Professor> getMinistrantes() {
        return ministrantes;
    }

    public Set<Aluno> getMatriculados() {
        return matriculados;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disciplina other = (Disciplina) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Disciplina [cargaHoraria=" + cargaHoraria + ", codigo=" + codigo + ", curso=" + curso + ", ementa="
                + ementa + ", id=" + id + ", matriculados=" + matriculados + ", ministrantes=" + ministrantes
                + ", nome=" + nome + "]";
    }

    public String getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

}