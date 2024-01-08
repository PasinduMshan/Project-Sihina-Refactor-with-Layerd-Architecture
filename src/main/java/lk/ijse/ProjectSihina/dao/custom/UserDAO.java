package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.UserDto;
import lk.ijse.ProjectSihina.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    boolean updateCredentials(User entity) throws SQLException;

    boolean checkNIC(String nic) throws SQLException;

    boolean checkCredentials(User entity) throws SQLException;

    String getUserID() throws SQLException;

    String getEmail(String nic) throws SQLException;

    boolean changeCredentials(String userName, String password, String NIC) throws SQLException;

    boolean checkCredentialsByPassword(String userName , String password) throws SQLException;
}
