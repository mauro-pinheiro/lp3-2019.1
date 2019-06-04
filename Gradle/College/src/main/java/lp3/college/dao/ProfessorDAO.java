package lp3.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lp3.college.entidades.Professor;
import lp3.college.infra.Database;

public class ProfessorDAO implements DAO<Professor> {
    private Connection conexao;

    public ProfessorDAO(Connection conexao){
        this.conexao = conexao;
    }

    @Override
    public Professor salva(Professor aluno) {
        String sql = "insert into professor(codigo, nome) values(?,?)";

        try(PreparedStatement statement = conexao.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, aluno.getCodigo());
            statement.setString(2, aluno.getNome());

            statement.execute();

            try(ResultSet keys = statement.getGeneratedKeys()){
                keys.next();
                aluno.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return aluno;
    }

    @Override
    public List<Professor> getAll() {
        String sql = "select * from Professor";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            try(ResultSet resultSet = statement.executeQuery(sql)) {

                final List<Professor> alunos = new ArrayList<>();

                while (resultSet.next()) {
                    Professor professor = monta(resultSet);
                    alunos.add(professor);
                }
                return alunos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Professor monta(ResultSet resultSet) {
        try {
            String nome = resultSet.getString("nome");
            String codigo = resultSet.getString("codigo");
            int id = resultSet.getInt("idProfessor");

            Professor curso = new Professor(codigo, nome);
            curso.setId(id);
            return curso;
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Professor buscaPorId(int id){
        String sql = "select * from Professor where id = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                    return monta(resultSet);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Professor buscaPorNome(String nome){
        String sql = "select * from Professor where nome = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, nome);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                    return monta(resultSet);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Professor buscaPorCodigo(String codigo){
        String sql = "select * from Professor where codigo = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, codigo);
            try(ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next())
                    return monta(resultSet);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Professor atualizar(Professor professor){
        String sql = "update Professor set codigo = ?, nome = ? where idProfessor = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, professor.getCodigo());
            statement.setString(2, professor.getNome());
            statement.setInt(3, professor.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return professor;
    }

    public static void main(String[] args) {
        new AlunoDAO(Database.getConexao()).getAll().forEach(System.out::println);
    }

    @Override
    public Professor deleta(Professor professor) {
        String sql = "delete from professor where idProfessor = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, professor.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return professor;
    }
}
