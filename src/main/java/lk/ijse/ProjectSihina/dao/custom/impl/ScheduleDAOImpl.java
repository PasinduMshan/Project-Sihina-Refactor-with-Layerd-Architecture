package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.ScheduleDAO;
import lk.ijse.ProjectSihina.entity.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public boolean save(Schedule entity) throws SQLException {

        return SQLUtil.execute("INSERT INTO Schedule VALUES (?,?,?,?,?,?)", entity.getClass_ID(), entity.getSub_ID(),
                entity.getTeacher_ID(), entity.getDay(), entity.getStartTime(), entity.getEndTime());

    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Schedule entity) throws SQLException {
        return false;
    }

    @Override
    public Schedule search(String Id) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteSchedule(Schedule entity) throws SQLException {
        return SQLUtil.execute("DELETE FROM Schedule WHERE class_id = ? AND Sub_id = ? AND Teacher_id = ? AND " +
                "Class_day = ? AND Start_Time = ? AND End_Time = ?", entity.getClass_ID(), entity.getSub_ID(),
                entity.getTeacher_ID(), entity.getDay(), entity.getStartTime(), entity.getEndTime());
    }

    @Override
    public ArrayList<Schedule> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Schedule ORDER BY Class_Day");

        ArrayList<Schedule> schedulesList = new ArrayList<>();

        while (resultSet.next()) {
            schedulesList.add(
                    new Schedule(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getTime(5).toLocalTime(),
                            resultSet.getTime(6).toLocalTime()
                    ));
        }
        return schedulesList;
    }

    @Override
    public String generateId() throws SQLException {
        return null;
    }

    @Override
    public String splitId(String currentId) {
        return null;
    }
}
