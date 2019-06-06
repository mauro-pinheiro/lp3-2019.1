package lp3.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lp3.college.entidades.Curso;
import lp3.college.infra.Database;

public class CursoDAO implements DAO<Curso> {
    private Connection conexao;

    public CursoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public Curso salva(Curso curso) {
        String sql = "insert into curso(codigo, nome) values(?,?)";

        try (PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, curso.getCodigo());
            statement.setString(2, curso.getNome());

            statement.execute();

            try (ResultSet keys = statement.getGeneratedKeys()) {
                keys.next();
                curso.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return curso;
    }

    @Override
    public List<Curso> getAll() {
        String sql = "select * from curso";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {

                final List<Curso> cidades = new ArrayList<>();

                while (resultSet.next()) {
                    Curso curso = monta(resultSet);
                    cidades.add(curso);
                }
                return cidades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Curso monta(ResultSet resultSet) {
        try {
            String nome = resultSet.getString("nome");
            String codigo = resultSet.getString("codigo");
            int id = resultSet.getInt("idCurso");

            Curso curso = new Curso(codigo, nome);
            curso.setId(id);
            return curso;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Curso buscaPorId(int id) {
        String sql = "select * from curso where idCurso = ?";

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

    public Curso buscaPorNome(String nome) {
        String sql = "select * from curso where nome = ?";

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

    public Curso buscaPorCodigo(String codigo) {
        String sql = "select * from curso where codigo = ?";

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

    public Curso atualiza(Curso curso) {
        String sql = "update curso set codigo = ?, nome = ? where idCurso = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setString(1, curso.getCodigo());
            statement.setString(2, curso.getNome());
            statement.setInt(3, curso.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return curso;
    }

    @Override
    public Curso deleta(Curso curso) {
        String sql = "delete from curso where idCurso = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setInt(1, curso.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return curso;
    }

    public static void main(String[] args) {
        Curso curso = new Curso("01", "Sistemas de Informacao");
        CursoDAO dao = new CursoDAO(Database.getConexao());
        dao.salva(curso);
        curso = dao.buscaPorCodigo("01");
        System.out.println(curso);
        curso.setNome("Sistemas de Informação");
        dao.atualiza(curso);
        curso = dao.buscaPorCodigo("01");
        System.out.println(curso);
        dao.deleta(curso);
        curso = dao.buscaPorCodigo("01");
        System.out.println(curso);
    }

    public int existe(Curso curso) {
        Curso c = buscaPorCodigo(curso.getCodigo());
        if(Objects.isNull(c)){
            return 0;
        } else {
            return c.getId();
        }
    }
}
