package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.entity.Schedule;

import java.sql.SQLException;

import java.util.List;

public interface ScheduleDAO extends CrudDAO<Schedule> {
    boolean deleteSchedule(Schedule entity) throws SQLException;


}
