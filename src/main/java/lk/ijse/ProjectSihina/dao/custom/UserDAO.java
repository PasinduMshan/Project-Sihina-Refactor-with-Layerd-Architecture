package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {
    boolean updateCredentials(String newUserName, String newPassword, String NIC) throws SQLException;

    List<UserDto> getAllUsers() throws SQLException;

    boolean checkNIC(String nic) throws SQLException;

    String generateNextUserId() throws SQLException;

    String splitUserId(String currentId);

    boolean userRegister(UserDto dto) throws SQLException;

    boolean deleteUser(String userId) throws SQLException;

    UserDto searchUser(String nic) throws SQLException;

    boolean updateUser(UserDto userDto) throws SQLException;

    boolean checkCredentials(String userNameNow, String passwordNow, String NIC) throws SQLException;
}
