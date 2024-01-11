package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.UserDto;

import java.sql.SQLException;

public interface SignupBO extends SuperBO {
    String generateNextUserId() throws SQLException;
    boolean userRegister(UserDto dto) throws SQLException;
}
