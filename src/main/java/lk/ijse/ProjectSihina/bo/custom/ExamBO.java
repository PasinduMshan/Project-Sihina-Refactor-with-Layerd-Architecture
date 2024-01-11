package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.ExamDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamBO extends SuperBO {
    String generateExamId() throws SQLException;
    ArrayList<SubjectDto> getAllSubject() throws SQLException;
    ArrayList<ClassDto> getAllClass() throws SQLException;
    ArrayList<ExamDto> getAllExam() throws SQLException;
    boolean AddExam(ExamDto dto) throws SQLException;
    boolean deleteExam(String examId) throws SQLException;
    ExamDto SearchExam(String examId) throws SQLException;
    boolean updateExam(ExamDto dto) throws SQLException;
}
