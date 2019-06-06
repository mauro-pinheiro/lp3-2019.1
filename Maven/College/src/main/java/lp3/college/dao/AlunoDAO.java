package lp3.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lp3.college.entidades.Aluno;
import lp3.college.entidades.Curso;
import lp3.college.infra.Database;

public class AlunoDAO implements DAO<Aluno> {
    private Connection conexao;

    public AlunoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public Aluno salva(Aluno aluno) {
        String sql = "insert into aluno(codigo, nome, rg, cpf, telefone, endereco) values(?,?,?,?,?,?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, aluno.getCodigo());
            statement.setString(2, aluno.getNome());
            statement.setString(3, aluno.getRg());
            statement.setString(4, aluno.getCpf());
            statement.setString(5, aluno.getTelefone());
            statement.setString(6, aluno.getEndereco());

            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
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

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {

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

    public List<Aluno> buscaPorCurso(Curso curso) {
        String sql = "select * from aluno where idCurso = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, curso.getId());
            try (ResultSet resultSet = statement.executeQuery()) {

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
            int idCurso = resultSet.getInt("idCurso");
            int id = resultSet.getInt("idAluno");

            Aluno aluno = new Aluno(codigo, nome, rg, cpf, telefone, endereco);
            aluno.setId(id);
            aluno.setCurso(new CursoDAO(conexao).buscaPorId(idCurso));
            return aluno;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Aluno buscaPorId(int id) {
        String sql = "select * from aluno where id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return monta(resultSet);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Aluno buscaPorNome(String nome) {
        String sql = "select * from aluno where nome = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, nome);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return monta(resultSet);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Aluno buscaPorCodigo(String codigo) {
        String sql = "select * from aluno where codigo = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, codigo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return monta(resultSet);
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Aluno atualiza(Aluno curso) {
        String sql = "update aluno set codigo = ?, nome = ?, rg = ?, cpf = ?, telefone = ?, endereco = ? where idAluno = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
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

    public Aluno atualiza(Aluno aluno, Curso curso){
        aluno.setCurso(curso);
        String sql = "update aluno set idCurso = ? where idAluno = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, curso.getId());
            statement.setInt(2, aluno.getId());
            statement.execute();
            return aluno;
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Aluno a = new Aluno("1","Mauro","1234","123","123","123");
        AlunoDAO dao = new AlunoDAO(Database.getConexao());
        int id = dao.existe(a);
        if(id <= 0){
            dao.salva(a);
        } else {
            a.setId(id);
            dao.atualiza(a);
        }
    }

    @Override
    public Aluno deleta(Aluno aluno) {
        String sql = "delete from aluno where codigo = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1,aluno.getCodigo());
            statement.execute();
            return aluno;
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public int existe(Aluno aluno) {
        Aluno a = buscaPorCodigo(aluno.getCodigo());
        if(Objects.isNull(a)){
            return 0;
        } else {
            return a.getId();
        }
    }
}
