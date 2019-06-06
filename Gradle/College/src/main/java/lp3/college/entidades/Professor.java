package lp3.college.entidades;

import java.util.HashSet;
import java.util.Set;

public class Professor implements EntidadeNomeada{
    private int id;
    private String codigo;
    private String nome;
    private Set<Disciplina> ministrando = new HashSet<>();

    public Professor(String codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
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

    public Set<Disciplina> getMinitrando() {
        return ministrando;
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
        Professor other = (Professor) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Professor [codigo=" + codigo + ", id=" + id + ", minitrando=" + ministrando + ", nome=" + nome + "]";
    }
}