package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.ClassBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.ClassDAO;
import lk.ijse.ProjectSihina.dao.custom.QueryDAO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.entity.Classes;
import lk.ijse.ProjectSihina.entity.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClassBOImpl implements ClassBO {
    ClassDAO classDAO = (ClassDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CLASS);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public String generateClassId() throws SQLException {
        return classDAO.generateId();
    }

    @Override
    public ArrayList<ClassDto> getAllClass() throws SQLException {
        ArrayList<ClassDto> classDtos = new ArrayList<>();

        ArrayList<Query> queries = queryDAO.getAllClass();

        for (Query query : queries) {
            classDtos.add(new ClassDto(query.getClassID(), query.getClassName(), query.getStu_Count()));
        }
        return classDtos;
    }

    @Override
    public boolean savaClass(ClassDto dto) throws SQLException {
        return classDAO.save(new Classes(dto.getClassID(), dto.getClassName()));
    }

    @Override
    public boolean isDeleteClass(String classId) throws SQLException {
        return classDAO.delete(classId);
    }

    @Override
    public boolean isUpdate(ClassDto dto) throws SQLException {
        return classDAO.update(new Classes(dto.getClassID(), dto.getClassName()));
    }

    @Override
    public ClassDto searchClass(String classId) throws SQLException {
        Classes classes = classDAO.search(classId);

        return new ClassDto(classes.getClassID(), classes.getClassName());
    }
}
