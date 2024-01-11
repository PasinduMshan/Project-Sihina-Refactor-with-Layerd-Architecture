package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    ArrayList<UserDto> getAllUsers() throws SQLException;
    boolean checkNIC(String nic) throws SQLException;
    boolean checkCredentials(String userNameNow, String passwordNow, String NIC) throws SQLException;
    boolean updateCredentials(String newUserName, String newPassword, String NIC) throws SQLException;
    boolean deleteUser(String userId) throws SQLException;
    UserDto searchUser(String nic) throws SQLException;
    boolean updateUser(UserDto userDto) throws SQLException;

}
