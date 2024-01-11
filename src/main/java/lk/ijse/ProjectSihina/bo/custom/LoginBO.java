package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean checkCredentials(String userName , String password) throws SQLException;
}
