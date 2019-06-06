package lp3.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lp3.college.entidades.Curso;
import lp3.college.entidades.Disciplina;
import lp3.college.entidades.Grade;
import lp3.college.infra.Database;

public class GradeDAO implements DAO<Grade> {
    private Connection conexao;

    public GradeDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public Grade salva(Grade grade) {
        String sql = "insert into grade(ano, idCurso, idDisciplina) values(?,?,?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setShort(1, grade.getAno());
            statement.setInt(2, grade.getCurso().getId());
            statement.setInt(3, grade.getDisciplina().getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return grade;
    }

    @Override
    public List<Grade> getAll() {
        String sql = "select * from grade";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {

                final List<Grade> grades = new ArrayList<>();

                while (resultSet.next()) {
                    Grade grade = monta(resultSet);
                    grades.add(grade);
                }
                return grades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Grade monta(ResultSet resultSet) {
        try {
            Short ano = resultSet.getShort("ano");
            int idCurso = resultSet.getInt("idCurso");
            int idDisciplina = resultSet.getInt("idDisciplina");
            Disciplina disciplina = new DisciplinaDAO(Database.getConexao()).buscaPorId(idDisciplina);
            Curso curso = new CursoDAO(Database.getConexao()).buscaPorId(idCurso);

            Grade grade = new Grade(ano, curso, disciplina);
            return grade;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Grade> buscaPorId(Short ano, Curso curso) {
        String sql = "select * from grade where ano = ? and idCurso = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setShort(1, ano);
            statement.setInt(2, curso.getId());
            System.out.println(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                final List<Grade> grades = new ArrayList<>();

                while (resultSet.next()) {
                    Grade grade = monta(resultSet);
                    grades.add(grade);
                }
                return grades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Grade deleta(Grade grade) {
        String sql = "delete from grade where ano = ? and idCurso = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setShort(1, grade.getAno());
            statement.setInt(2, grade.getCurso().getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return grade;
    }

    @Override
    public int existe(Grade t) {
        return 0;
    }
}
