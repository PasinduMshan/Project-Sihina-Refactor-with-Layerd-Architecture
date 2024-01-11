package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;

import java.sql.SQLException;

public interface ForgetPasswordBO extends SuperBO {
    String getEmail(String nic) throws SQLException;
}
