package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.ClassDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassBO extends SuperBO {
    String generateClassId() throws SQLException;
    ArrayList<ClassDto> getAllClass() throws SQLException;
    boolean savaClass(ClassDto dto) throws SQLException;
    boolean isDeleteClass(String classId) throws SQLException;
    boolean isUpdate(ClassDto dto) throws SQLException;
    ClassDto searchClass(String classId) throws SQLException;

}
