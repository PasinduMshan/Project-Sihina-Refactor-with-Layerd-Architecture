package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.dao.SuperDAO;
import lk.ijse.ProjectSihina.entity.Query;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    ArrayList<Query> getAllClass() throws SQLException;

    ArrayList<Query> getTodaySchedule(LocalDate date) throws SQLException;

    ArrayList<Query> getTodayExams(LocalDate date) throws SQLException;
}
