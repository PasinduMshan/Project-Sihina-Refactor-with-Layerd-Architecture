package lk.ijse.ProjectSihina.bo.custom;

import javafx.scene.image.Image;
import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.TeacherDto;

import java.sql.*;
import java.util.ArrayList;

public interface TeacherBO extends SuperBO {
    boolean addTeacher(TeacherDto dto) throws SQLException;
    boolean deleteTeacher(String id) throws SQLException;
    boolean updateTeacher(TeacherDto dto) throws SQLException;
    TeacherDto searchTeacher(String id) throws SQLException;
    ArrayList<TeacherDto> getAllTeachers() throws SQLException;
    String generateTeacherId() throws SQLException;
}
