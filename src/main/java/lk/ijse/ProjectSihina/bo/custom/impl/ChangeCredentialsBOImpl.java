package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.ChangeCredentialsBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.UserDAO;
import lk.ijse.ProjectSihina.entity.User;

import java.sql.SQLException;

public class ChangeCredentialsBOImpl implements ChangeCredentialsBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean updateCredentials(String newUserName, String newPassword, String NIC) throws SQLException {
        return userDAO.update(new User(newUserName, newPassword, NIC));
    }
}
