package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.ForgetPasswordBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.UserDAO;

import java.sql.SQLException;

public class ForgetPasswordBOImpl implements ForgetPasswordBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String getEmail(String nic) throws SQLException {
        return userDAO.getEmail(nic);
    }
}
