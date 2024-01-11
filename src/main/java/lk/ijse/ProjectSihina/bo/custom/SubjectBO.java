package lk.ijse.ProjectSihina.bo.custom;


import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.dto.TeacherDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubjectBO extends SuperBO {
    boolean saveSubject(SubjectDto dto) throws SQLException;
    boolean deleteSubject(String id) throws SQLException;
    boolean updateSubject(SubjectDto dto) throws SQLException;
    SubjectDto searchSubject(String id) throws SQLException;
    ArrayList<SubjectDto> getAllDetails() throws SQLException;
    String generateSubId() throws SQLException;
    ArrayList<TeacherDto> getAllTeacher() throws SQLException;
}