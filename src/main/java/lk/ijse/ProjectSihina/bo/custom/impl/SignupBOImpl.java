package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.SignupBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.UserDAO;
import lk.ijse.ProjectSihina.dto.UserDto;
import lk.ijse.ProjectSihina.entity.User;

import java.sql.SQLException;

public class SignupBOImpl implements SignupBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public String generateNextUserId() throws SQLException {
        return userDAO.generateId();
    }
    @Override
    public boolean userRegister(UserDto dto) throws SQLException {
        return userDAO.save(new User(dto.getUserId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getNIC(),
                dto.getUserName(), dto.getPassword()));
    }
}
