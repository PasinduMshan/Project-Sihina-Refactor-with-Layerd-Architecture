package lk.ijse.ProjectSihina.dao.custom;


import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.entity.Attendance;

import java.sql.*;

public interface AttendanceDAO extends CrudDAO<Attendance> {
    String getAllAttendant(String stu_id, String subject, String month, String stu_class) throws SQLException;

}
