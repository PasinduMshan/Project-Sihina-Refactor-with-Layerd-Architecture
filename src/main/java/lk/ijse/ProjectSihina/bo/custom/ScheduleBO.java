package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.ScheduleDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.dto.TeacherDto;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ScheduleBO extends SuperBO {
    ArrayList<TeacherDto> getAllTeacher() throws SQLException;
    ArrayList<SubjectDto> getAllSubject() throws SQLException;
    ArrayList<ClassDto> getAllClass() throws SQLException;
    ArrayList<ScheduleDto> getAllSchedule() throws SQLException;
    boolean AddSchedule(ScheduleDto dto) throws SQLException;
    boolean DeleteSchedule(ScheduleDto dto) throws SQLException;
}
