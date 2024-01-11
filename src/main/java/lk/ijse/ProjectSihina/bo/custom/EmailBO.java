package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.ClassDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmailBO extends SuperBO {
    ArrayList<ClassDto> getAllClass() throws SQLException;
    ArrayList<String> getAllEmailFromStudent(String student) throws SQLException;
    ArrayList<String> getAllEmailFromTeacher(String teacher) throws SQLException;
    ArrayList<String> getAllEmailsByClass(String stuClass) throws SQLException;

}
