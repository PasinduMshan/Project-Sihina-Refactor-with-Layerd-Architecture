package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.entity.Classes;

import java.sql.SQLException;

public interface ClassDAO extends CrudDAO<Classes> {
    String getClassID(String ClassName) throws SQLException;

    String getClassName(String class_Id) throws SQLException;
}
