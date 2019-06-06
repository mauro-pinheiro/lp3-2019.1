package lp3.college.entidades;

import java.util.HashSet;
import java.util.Set;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class Aluno implements EntidadeNomeada{
    private int id;
    private String codigo;
    private String nome;
    private String rg;
    private String cpf;
    private String telefone;
    private String endereco;
    private Curso curso;
    private Set<Disciplina> cursando = new HashSet<>();

    public Aluno(String codigo, String nome, String rg, String cpf, String telefone, String endereco){
        this.codigo = codigo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Set<Disciplina> getCursando() {
        return cursando;
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
        Aluno other = (Aluno) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Aluno [codigo=" + codigo + ", cpf=" + cpf + ", curso=" + curso + ", endereco=" + endereco + ", id=" + id
                + ", nome=" + nome + ", rg=" + rg + ", telefone=" + telefone + "]";
    }

    public ObservableValue<String> cursoProperty(){
        return new SimpleObjectProperty<String>(curso.getNome());
    }
}