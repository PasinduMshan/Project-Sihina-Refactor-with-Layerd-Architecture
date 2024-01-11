package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.EmailBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.QueryDAO;
import lk.ijse.ProjectSihina.dao.custom.StudentDAO;
import lk.ijse.ProjectSihina.dao.custom.TeacherDAO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.entity.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmailBOImpl implements EmailBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEACHER);

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
    public ArrayList<String> getAllEmailFromStudent(String student) throws SQLException {
        return studentDAO.getAllEmail(student);
    }

    @Override
    public ArrayList<String> getAllEmailFromTeacher(String teacher) throws SQLException {
        return teacherDAO.getAllEmail(teacher);
    }

    @Override
    public ArrayList<String> getAllEmailsByClass(String stuClass) throws SQLException {
        return studentDAO.getAllEmailsByClass(stuClass);
    }
}
