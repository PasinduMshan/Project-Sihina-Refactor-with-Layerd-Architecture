package lk.ijse.ProjectSihina.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    boolean save(T entity) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(T entity) throws SQLException;

    T search(String Id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    String generateId() throws SQLException;

    String splitId(String currentId);
}
