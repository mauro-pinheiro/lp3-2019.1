package lp3.college.entidades;

public class Curso{
    private int id;
    private String codigo;
    private String nome;

    public Curso(String codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public void setId(int id){
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        Curso other = (Curso) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Curso [codigo=" + codigo + ", idCurso=" + id + ", nome=" + nome + "]";
    }
}