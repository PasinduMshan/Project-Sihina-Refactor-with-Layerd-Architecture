package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.UserDAO;
import lk.ijse.ProjectSihina.dto.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDAO {

    @Override
    public boolean updateCredentials(String newUserName, String newPassword, String NIC) throws SQLException {
        return SQLUtil.execute("UPDATE user SET User_Name = ?, Password = ? WHERE NIC = ?",newUserName,newPassword,NIC);
    }

    @Override
    public List<UserDto> getAllUsers() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");

        ArrayList<UserDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new UserDto(
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
    public String generateNextUserId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitUserId(resultSet.getString(1));
        }
        return "U001";
    }

    @Override
    public String splitUserId(String currentId) {
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
    public boolean userRegister(UserDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES(?,?,?,?,?,?,?)",dto.getUserId(),dto.getFirstName(),
                dto.getLastName(),dto.getEmail(),dto.getNIC(),dto.getUserName(),dto.getPassword());
    }

    @Override
    public boolean deleteUser(String userId) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE user_id = ?",userId);
    }
    @Override
    public UserDto searchUser(String nic) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE NIC = ?",nic);

        UserDto userDto = null;

        if (resultSet.next()) {
            String userId = resultSet.getString(1);
            String FirstName = resultSet.getString(2);
            String LastName = resultSet.getString(3);
            String Email = resultSet.getString(4);
            String NIC = resultSet.getString(5);
            String userName = resultSet.getString(6);
            String password = resultSet.getString(7);

            userDto = new UserDto(userId, FirstName, LastName, Email, NIC, userName, password);
        }
        return userDto;
    }

    @Override
    public boolean updateUser(UserDto userDto) throws SQLException {
        return SQLUtil.execute("UPDATE user SET First_Name = ?, Last_Name = ?, Email = ?, NIC = ? WHERE user_id = ?"
                ,userDto.getFirstName(),userDto.getLastName(),userDto.getEmail(),userDto.getNIC(),userDto.getUserId());
    }

    @Override
    public boolean checkCredentials(String userNameNow, String passwordNow, String NIC) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT User_Name , Password FROM user WHERE NIC = ?",NIC);

        String U_Name = null;
        String Password = null;

        if (resultSet.next()) {
            U_Name = resultSet.getString(1);
            Password = resultSet.getString(2);
        }

        if (U_Name.equals(userNameNow) && Password.equals(passwordNow)) {
            return true;
        }

        return false;
    }
}
