package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.AttendantDto;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    ArrayList<SubjectDto> getAllSubject() throws SQLException;
    ArrayList<ClassDto> getAllClass() throws SQLException;
    ArrayList<AttendantDto> getAllAttendance() throws SQLException;
    boolean AddAttendant(AttendantDto dto) throws SQLException;
    boolean deleteAttendant(String attId) throws SQLException;
    String generateNextAttId() throws SQLException;
    AttendantDto searchAttendant(String attId) throws SQLException;
    boolean UpdateAttendant(AttendantDto dto) throws SQLException;
    String searchStudent(String stuId) throws SQLException;
}
