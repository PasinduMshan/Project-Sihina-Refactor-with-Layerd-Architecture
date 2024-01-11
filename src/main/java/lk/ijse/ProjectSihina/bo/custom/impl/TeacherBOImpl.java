package lk.ijse.ProjectSihina.bo.custom.impl;

import javafx.scene.image.Image;
import lk.ijse.ProjectSihina.bo.custom.TeacherBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.TeacherDAO;
import lk.ijse.ProjectSihina.dto.TeacherDto;
import lk.ijse.ProjectSihina.entity.Teacher;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;


public class TeacherBOImpl implements TeacherBO {

    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEACHER);

    @Override
    public boolean addTeacher(TeacherDto dto) throws SQLException {
        return teacherDAO.save(new Teacher(dto.getId(), dto.getName(), dto.getAddress(), dto.getEmail(), dto.getSubjects(),
                dto.getContactNo(), dto.getImageTeacher()));
    }

    @Override
    public boolean deleteTeacher(String id) throws SQLException {
        return teacherDAO.delete(id);
    }

    @Override
    public boolean updateTeacher(TeacherDto dto) throws SQLException {
        return teacherDAO.update(new Teacher(dto.getId(), dto.getName(), dto.getAddress(), dto.getEmail(), dto.getSubjects(),
                dto.getContactNo(), dto.getImageTeacher()));
    }

    @Override
    public TeacherDto searchTeacher(String id) throws SQLException {
        Teacher teacher = teacherDAO.search(id);

        return new TeacherDto(teacher.getId(), teacher.getName(), teacher.getAddress(), teacher.getEmail(),
                teacher.getSubjects(), teacher.getContactNo(), teacher.getImageTeacher());
    }

    @Override
    public ArrayList<TeacherDto> getAllTeachers() throws SQLException {
        ArrayList<TeacherDto> teacherDtos = new ArrayList<>();

        ArrayList<Teacher> teachers = teacherDAO.getAll();

        for (Teacher teacher : teachers) {
            teacherDtos.add(new TeacherDto(teacher.getId(), teacher.getName(), teacher.getAddress(), teacher.getEmail(),
                    teacher.getSubjects(), teacher.getContactNo(), teacher.getImageTeacher()));
        }

        return teacherDtos;
    }

    @Override
    public String generateTeacherId() throws SQLException {
        return teacherDAO.generateId();
    }

}
