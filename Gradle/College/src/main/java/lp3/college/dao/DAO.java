package lp3.college.dao;

import java.sql.ResultSet;
import java.util.List;

public interface DAO<T> {
    public T salva(T t);

    public List<T> getAll();

    public T monta(ResultSet resultSet);

    public T deleta(T t);
}
