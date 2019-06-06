package lp3.college.entidades;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class Ministra {
    Professor professor;
    Disciplina disciplina;

    public Ministra(Professor p, Disciplina d){
        this.professor = p;
        this.disciplina = d;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
        result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
        Ministra other = (Ministra) obj;
        if (disciplina == null) {
            if (other.disciplina != null)
                return false;
        } else if (!disciplina.equals(other.disciplina))
            return false;
        if (professor == null) {
            if (other.professor != null)
                return false;
        } else if (!professor.equals(other.professor))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Ministra [disciplina=" + disciplina + ", professor=" + professor + "]";
    }

    public ObservableValue<String> professorProperty(){
        return new SimpleObjectProperty<String>(professor.getNome());
    }

    public ObservableValue<String> disciplinaProperty(){
        return new SimpleObjectProperty<String>(disciplina.getNome());
    }
}