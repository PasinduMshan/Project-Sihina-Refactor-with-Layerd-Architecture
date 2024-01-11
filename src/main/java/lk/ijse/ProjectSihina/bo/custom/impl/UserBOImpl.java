package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.UserBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.UserDAO;
import lk.ijse.ProjectSihina.dto.UserDto;
import lk.ijse.ProjectSihina.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public ArrayList<UserDto> getAllUsers() throws SQLException {
        ArrayList<UserDto> userDTOS = new ArrayList<>();

        ArrayList<User> users =  userDAO.getAll();

        for (User user : users) {
            userDTOS.add(new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getNIC()));
        }
        return userDTOS;
    }

    @Override
    public boolean checkNIC(String nic) throws SQLException {
        return userDAO.checkNIC(nic);
    }

    @Override
    public boolean checkCredentials(String userNameNow, String passwordNow, String NIC) throws SQLException {
        return userDAO.checkCredentials(new User(userNameNow, passwordNow, NIC));
    }

    @Override
    public boolean updateCredentials(String newUserName, String newPassword, String NIC) throws SQLException {
        return userDAO.updateCredentials(new User(newUserName, newPassword, NIC));
    }

    @Override
    public boolean deleteUser(String userId) throws SQLException {
        return userDAO.delete(userId);
    }

    @Override
    public UserDto searchUser(String nic) throws SQLException {
        User user = userDAO.search(nic);

        return new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getNIC(),
                user.getUserName(), user.getPassword());
    }

    @Override
    public boolean updateUser(UserDto userDto) throws SQLException {
        return userDAO.update(new User(userDto.getUserId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(),
                userDto.getNIC()));
    }
}
