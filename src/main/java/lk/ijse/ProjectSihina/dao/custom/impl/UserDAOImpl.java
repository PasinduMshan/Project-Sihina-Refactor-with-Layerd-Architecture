package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.User.UserConnection;
import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.UserDAO;
import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.UserDto;
import lk.ijse.ProjectSihina.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean updateCredentials(User entity) throws SQLException {
        return SQLUtil.execute("UPDATE user SET User_Name = ?, Password = ? WHERE NIC = ?",entity.getUserName(),
                entity.getPassword(),entity.getNIC());
    }

    @Override
    public List<User> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");

        ArrayList<User> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return dtoList;
    }

    @Override
    public boolean checkNIC(String nic) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT NIC FROM user WHERE NIC = ?",nic);

        if (resultSet.next()) {
            String NIC = resultSet.getString(1);

            if (NIC.equals(nic)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "U001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("U0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "U00"+id;
            }else {
                if (length < 3){
                    return "U0"+id;
                }else {
                    return "U"+id;
                }
            }
        }
        return "U001";
    }

    @Override
    public boolean save(User entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES(?,?,?,?,?,?,?)",entity.getUserId(),entity.getFirstName(),
                entity.getLastName(),entity.getEmail(),entity.getNIC(),entity.getUserName(),entity.getPassword());
    }

    @Override
    public boolean delete(String userId) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE user_id = ?",userId);
    }
    @Override
    public User search(String nic) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE NIC = ?",nic);

        User entity = null;

        if (resultSet.next()) {
            String userId = resultSet.getString(1);
            String FirstName = resultSet.getString(2);
            String LastName = resultSet.getString(3);
            String Email = resultSet.getString(4);
            String NIC = resultSet.getString(5);
            String userName = resultSet.getString(6);
            String password = resultSet.getString(7);

            entity = new User(userId, FirstName, LastName, Email, NIC, userName, password);
        }
        return entity;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return SQLUtil.execute("UPDATE user SET First_Name = ?, Last_Name = ?, Email = ?, NIC = ? WHERE user_id = ?"
                ,entity.getFirstName(),entity.getLastName(),entity.getEmail(),entity.getNIC(),entity.getUserId());
    }

    @Override
    public boolean checkCredentials(User entity) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT User_Name , Password FROM user WHERE NIC = ?",entity.getNIC());

        String U_Name = null;
        String Password = null;

        if (resultSet.next()) {
            U_Name = resultSet.getString(1);

            Password = resultSet.getString(2);
        }

        if (U_Name.equals(entity.getUserName()) && Password.equals(entity.getPassword())) {
            return true;
        }

        return false;
    }

    @Override
    public String getUserID() throws SQLException {
        String userName = UserConnection.getInstance().getUserName();
        String Password = UserConnection.getInstance().getPassword();

        ResultSet resultSet = SQLUtil.execute("SELECT user_id FROM user WHERE User_Name = ? AND Password = ?",
                userName, Password);

        resultSet.next();
        return resultSet.getString(1);

    }

    @Override
    public String getEmail(String nic) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Email FROM user WHERE NIC = ?", nic);

        String Email = null;

        if (resultSet.next()) {
            Email = resultSet.getString(1);
        }
        return Email;
    }

    @Override
    public boolean changeCredentials(String userName, String password, String NIC) throws SQLException {
        return SQLUtil.execute("UPDATE user SET user_Name = ?, Password = ? WHERE NIC = ?", userName, password, NIC);
    }

    @Override
    public boolean checkCredentialsByPassword(String userName , String password) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE Password = ?", password);

        String U_Name = null;
        String PassWord = null;

        if (resultSet.next()) {
            U_Name = resultSet.getString(6);
            PassWord = resultSet.getString(7);
        }

        if (userName.equals(U_Name) && password.equals(PassWord)) {
            return true;
        }
        return false;
    }
}
