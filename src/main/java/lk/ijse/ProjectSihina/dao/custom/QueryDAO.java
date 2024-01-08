package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.dao.SuperDAO;
import lk.ijse.ProjectSihina.entity.Query;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Query> getAllClass() throws SQLException;

    List<Query> getTodaySchedule(LocalDate date) throws SQLException;

    List<Query> getTodayExams(LocalDate date) throws SQLException;
}
