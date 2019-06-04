package lp3.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lp3.college.entidades.Disciplina;
import lp3.college.infra.Database;

public class DisciplinaDAO implements DAO<Disciplina> {
    private Connection conexao;

    public DisciplinaDAO(Connection conexao){
        this.conexao = conexao;
    }

    @Override
    public Disciplina salva(Disciplina disciplina) {
        String sql = "insert into disciplina(codigo, nome, ano, ementa) values(?,?,?,?)";

        try(PreparedStatement statement = conexao.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, disciplina.getCodigo());
            statement.setString(2, disciplina.getNome());
            statement.setString(3, disciplina.getAno());
            statement.setString(4, disciplina.getEmenta());

            statement.execute();

            try(ResultSet keys = statement.getGeneratedKeys()){
                keys.next();
                disciplina.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return disciplina;
    }

    @Override
    public List<Disciplina> getAll() {
        String sql = "select * from disciplina";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            try(ResultSet resultSet = statement.executeQuery(sql)) {

                final List<Disciplina> cidades = new ArrayList<>();

                while (resultSet.next()) {
                    Disciplina disciplina = monta(resultSet);
                    cidades.add(disciplina);
                }
                return cidades;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Disciplina monta(ResultSet resultSet) {
        try {
            String nome = resultSet.getString("nome");
            String codigo = resultSet.getString("codigo");
            String ano = resultSet.getString("ano");
            String ementa = resultSet.getString("ementa");
            String ch = resultSet.getString("ch");
            int id = resultSet.getInt("idCurso");

            Disciplina curso = new Disciplina(codigo, nome, ano, ementa, ch);
            curso.setId(id);
            return curso;
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Disciplina buscaPorId(int id){
        String sql = "select * from disciplina where id = ?";

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

    public Disciplina buscaPorNome(String nome){
        String sql = "select * from disciplina where nome = ?";

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

    public Disciplina buscaPorCodigo(String codigo){
        String sql = "select * from disciplina where codigo = ?";

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

    public Disciplina atualiza(Disciplina curso){
        String sql = "update disciplina set codigo = ?, nome = ?, ano = ?, ementa = ? where idDisciplina = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, curso.getCodigo());
            statement.setString(2, curso.getNome());
            statement.setString(3, curso.getAno());
            statement.setString(4, curso.getEmenta());
            statement.setInt(5, curso.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return curso;
    }

    public static void main(String[] args) {
        new DisciplinaDAO(Database.getConexao()).getAll().forEach(System.out::println);
    }

    @Override
    public Disciplina deleta(Disciplina disciplina) {
        String sql = "delete from disciplina where idDisciplina = ?";

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setInt(1, disciplina.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return disciplina;
    }
}
