package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.RegistrationDAO;
import lk.ijse.ProjectSihina.entity.Registration;

import java.sql.*;
import java.util.ArrayList;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(Registration entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Registration VALUES (?,?,?,?,?,?,?)", entity.getStuId(), entity.getPayId(),
                entity.getStuName(), entity.getClassId(), entity.getRegFee(), entity.getDate(), entity.getTime());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Registration entity) throws SQLException {
        return false;
    }

    @Override
    public Registration search(String Id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Registration> getAll() throws SQLException {
        return null;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    @Override
    public String splitId(String currentId) {
        return null;
    }
}
