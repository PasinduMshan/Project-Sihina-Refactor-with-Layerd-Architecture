package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    String generateStudentId() throws SQLException;
    ArrayList<StudentDto> getAllStudent() throws SQLException;
    ArrayList<ClassDto> getAllClass() throws SQLException;
    boolean deleteStudent(String id) throws SQLException;
    boolean updateStudent(StudentDto dto) throws SQLException;
    StudentDto searchStudent(String Id) throws SQLException;

}
