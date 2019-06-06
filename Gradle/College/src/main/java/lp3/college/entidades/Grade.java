package lp3.college.entidades;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class Grade {
    private Short ano;
    private Curso curso;
    private Disciplina disciplina;

    public Grade(Short ano, Curso curso, Disciplina disciplina){
        this.ano = ano;
        this.curso = curso;
        this.disciplina = disciplina;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public ObservableValue<String> cursoProperty(){
        return new SimpleObjectProperty<String>(curso.getNome());
    }

    public ObservableValue<String> disciplinaProperty(){
        return new SimpleObjectProperty<String>(disciplina.getNome());
    }
}