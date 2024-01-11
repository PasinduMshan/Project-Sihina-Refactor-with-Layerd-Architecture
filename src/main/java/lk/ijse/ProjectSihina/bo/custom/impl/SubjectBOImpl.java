package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.SubjectBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.SubjectDAO;
import lk.ijse.ProjectSihina.dao.custom.TeacherDAO;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.dto.TeacherDto;
import lk.ijse.ProjectSihina.entity.Subject;
import lk.ijse.ProjectSihina.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectBOImpl implements SubjectBO {
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEACHER);

    @Override
    public boolean saveSubject(SubjectDto dto) throws SQLException {
        return subjectDAO.save(new Subject(dto.getId(), dto.getSubject(), dto.getAvailableClass(), dto.getTeacherName(),
                dto.getMonthlyAmount()));
    }

    @Override
    public boolean deleteSubject(String id) throws SQLException {
        return subjectDAO.delete(id);
    }

    @Override
    public boolean updateSubject(SubjectDto dto) throws SQLException {
        return subjectDAO.update(new Subject(dto.getId(), dto.getSubject(), dto.getAvailableClass(), dto.getTeacherName(),
                dto.getMonthlyAmount()));
    }

    @Override
    public SubjectDto searchSubject(String id) throws SQLException {

        Subject entity = subjectDAO.search(id);

        return new SubjectDto(entity.getId(), entity.getSubject(), entity.getAvailableClass(), entity.getTeacherName(),
                entity.getMonthlyAmount());
    }

    @Override
    public ArrayList<SubjectDto> getAllDetails() throws SQLException {
        ArrayList<SubjectDto> subjectDtos = new ArrayList<>();

        ArrayList<Subject> subjects = subjectDAO.getAll();

        for (Subject subject : subjects) {
            subjectDtos.add(new SubjectDto(subject.getId(), subject.getSubject(), subject.getAvailableClass(),
                    subject.getTeacherName(), subject.getMonthlyAmount()));
        }

        return subjectDtos;
    }

    @Override
    public String generateSubId() throws SQLException {
        return subjectDAO.generateId();
    }

    @Override
    public ArrayList<TeacherDto> getAllTeacher() throws SQLException {
        ArrayList<TeacherDto> teacherDtos = new ArrayList<>();

        ArrayList<Teacher> teachers = teacherDAO.getAllTeacherName();

        for (Teacher teacher : teachers) {
            teacherDtos.add(new TeacherDto(teacher.getName()));
        }

        return teacherDtos;
    }

}
