package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;

import java.sql.SQLException;

public interface ChangeCredentialsBO extends SuperBO {
    boolean updateCredentials(String newUserName, String newPassword, String NIC) throws SQLException;

}
