package lk.ijse.ProjectSihina.bo.custom.impl;


import lk.ijse.ProjectSihina.bo.custom.LoginBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.UserDAO;
import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean checkCredentials(String userName, String password) throws SQLException {
        return userDAO.checkCredentialsByPassword(userName, password);
    }
}
