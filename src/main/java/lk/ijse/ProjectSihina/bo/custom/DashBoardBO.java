package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.DashBordScheduleDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface DashBoardBO extends SuperBO {
    ArrayList<DashBordScheduleDto> getTodaySchedule(LocalDate date) throws SQLException;
    ArrayList<DashBordScheduleDto> getTodayExams(LocalDate date) throws SQLException;
    String getSubjectCount() throws SQLException;
    String getTeacherCount() throws SQLException;
    String getStudentCount() throws SQLException;
    String getSumOfAmount(LocalDate date) throws SQLException;
}
