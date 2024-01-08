package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.entity.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SubjectDAO extends CrudDAO<Subject> {
    double getAmountINSubject(String subject) throws SQLException;

    String getSubjectID(String subject) throws SQLException;

    List<Subject> getAllSubjectName() throws SQLException;

    String getSubjectName(String sub_Id) throws SQLException;

    String getSubjectCount() throws SQLException;

}
