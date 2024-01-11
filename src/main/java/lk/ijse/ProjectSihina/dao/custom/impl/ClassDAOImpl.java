package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.ClassDAO;
import lk.ijse.ProjectSihina.entity.Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDAOImpl implements ClassDAO {
    @Override
    public boolean save(Classes entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Class VALUES (?,?)", entity.getClassID(), entity.getClassName());
    }

    @Override
    public boolean delete(String classId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Class WHERE class_id = ?", classId);
    }

    @Override
    public boolean update(Classes entity) throws SQLException {
        return SQLUtil.execute("UPDATE Class SET Name = ? WHERE class_id = ?", entity.getClassName(),
                entity.getClassID());
    }

    @Override
    public Classes search(String classId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Class WHERE class_id = ?", classId);

        Classes entity = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);

            entity = new Classes(id, name);
        }
        return entity;
    }

    @Override
    public ArrayList<Classes> getAll() throws SQLException {
        return null;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT class_id FROM Class ORDER BY class_id DESC LIMIT 1");
        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "C001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("C0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "C00"+id;
            }else {
                if (length < 3){
                    return "C0"+id;
                }else {
                    return "C"+id;
                }
            }
        }
        return "C001";
    }

    @Override
    public String getClassID(String ClassName) throws SQLException {

        ResultSet resultSet  = SQLUtil.execute("SELECT class_Id FROM Class WHERE Name = ?", ClassName);

        String Class_Id = null;

        if (resultSet.next()){
            Class_Id = resultSet.getString(1);
        }
        return Class_Id;
    }

    @Override
    public String getClassName(String class_Id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM Class WHERE class_id = ?", class_Id);

        String ClassName = null;

        if (resultSet.next()) {
            ClassName = resultSet.getString(1);
        }
        return ClassName;
    }
}

