package lp3.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lp3.college.entidades.Aluno;
import lp3.college.infra.Database;

public class AlunoDAO implements DAO<Aluno> {
    private Connection conexao;

    public AlunoDAO(Connection conexao){
        this.conexao = conexao;
    }

    @Override
    public Aluno salva(Aluno aluno) {
        String sql = "insert into aluno(codigo, nome, rg, cpf, telefone, endereco) values(?,?,?,?,?,?)";

        try(PreparedStatement statement = conexao.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, aluno.getCodigo());
            statement.setString(2, aluno.getNome());
            statement.setString(3, aluno.getRg());
            statement.setString(4, aluno.getCpf());
            statement.setString(5, aluno.getTelefone());
            statement.setString(6, aluno.getEndereco());

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
    public List<Aluno> getAll() {
        String sql = "select * from aluno";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            try(ResultSet resultSet = statement.executeQuery(sql)) {

                final List<Aluno> alunos = new ArrayList<>();

                while (resultSet.next()) {
                    Aluno aluno = monta(resultSet);
                    alunos.add(aluno);
                }
                return alunos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Aluno monta(ResultSet resultSet) {
        try {
            String nome = resultSet.getString("nome");
            String codigo = resultSet.getString("codigo");
            String rg = resultSet.getString("rg");
            String cpf = resultSet.getString("cpf");
            String telefone = resultSet.getString("telefone");
            String endereco = resultSet.getString("endereco");
            int id = resultSet.getInt("idCurso");

            Aluno curso = new Aluno(codigo, nome, rg, cpf, telefone, endereco);
            curso.setId(id);
            return curso;
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Aluno buscaPorId(int id){
        String sql = "select * from aluno where id = ?";

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

    public Aluno buscaPorNome(String nome){
        String sql = "select * from aluno where nome = ?";

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

    public Aluno buscaPorCodigo(String codigo){
        String sql = "select * from aluno where codigo = ?";

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

    public Aluno atualizar(Aluno curso){
        String sql = "update aluno set codigo = ?, nome = ?, rg = ?, cpf = ?, telefone ?, endereco = ? where idAluno = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, curso.getCodigo());
            statement.setString(2, curso.getNome());
            statement.setString(3, curso.getRg());
            statement.setString(4, curso.getCpf());
            statement.setString(5, curso.getTelefone());
            statement.setString(6, curso.getEndereco());
            statement.setInt(7, curso.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return curso;
    }

    public static void main(String[] args) {
        new ProfessorDAO(Database.getConexao()).getAll().forEach(System.out::println);
    }

    @Override
    public Aluno deleta(Aluno t) {
        return null;
    }
}
