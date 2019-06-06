package lp3.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lp3.college.entidades.Disciplina;
import lp3.college.entidades.Ministra;
import lp3.college.entidades.Professor;
import lp3.college.infra.Database;

public class MinistraDAO implements DAO<Ministra> {
    private Connection conexao;

    public MinistraDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public Ministra salva(Ministra ministra) {
        String sql = "insert into ministra(idProfessor, idDisciplina) values(?,?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setInt(1, ministra.getProfessor().getId());
            statement.setInt(2, ministra.getDisciplina().getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return ministra;
    }

    @Override
    public List<Ministra> getAll() {
        String sql = "select * from ministra";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {

                final List<Ministra> ministras = new ArrayList<>();

                while (resultSet.next()) {
                    Ministra ministra = monta(resultSet);
                    ministras.add(ministra);
                }
                return ministras;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Ministra monta(ResultSet resultSet) {
        try {
            int idProfessor = resultSet.getInt("idProfessor");
            int idDisciplina = resultSet.getInt("idDisciplina");
            Disciplina disciplina = new DisciplinaDAO(Database.getConexao()).buscaPorId(idDisciplina);
            Professor curso = new ProfessorDAO(Database.getConexao()).buscaPorId(idProfessor);

            Ministra grade = new Ministra(curso, disciplina);
            return grade;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public List<Ministra> buscaPorDisciplina(Disciplina disc){
        String sql = "select * from ministra where idDisciplina = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, disc.getId());
            try (ResultSet resultSet = statement.executeQuery()) {

                final List<Ministra> ministras = new ArrayList<>();

                while (resultSet.next()) {
                    Ministra ministra = monta(resultSet);
                    ministras.add(ministra);
                }
                return ministras;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Ministra> buscaPorProfessor(Professor prof){
        String sql = "select * from ministra where idProfessor = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, prof.getId());
            try (ResultSet resultSet = statement.executeQuery()) {

                final List<Ministra> ministras = new ArrayList<>();

                while (resultSet.next()) {
                    Ministra ministra = monta(resultSet);
                    ministras.add(ministra);
                }
                return ministras;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Ministra deleta(Ministra ministra) {
        String sql = "delete from ministra where idProfessor = ? and idDisciplina = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, ministra.getProfessor().getId());
            statement.setInt(2, ministra.getDisciplina().getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return ministra;
    }

    @Override
    public int existe(Ministra t) {
        return 0;
    }
}
