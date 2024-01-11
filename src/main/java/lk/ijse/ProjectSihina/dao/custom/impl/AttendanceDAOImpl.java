package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.AttendanceDAO;
import lk.ijse.ProjectSihina.entity.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAOImpl implements AttendanceDAO {

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Att_id FROM Attendance ORDER BY Att_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "At001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("At0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "At00"+id;
            }else {
                if (length < 3){
                    return "At0"+id;
                } else {
                    return "At"+id;
                }
            }
        }
        return "At001";
    }

    @Override
    public boolean save(Attendance entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Attendance VALUES (?,?,?,?,?,?,?,?)", entity.getAtt_id(), entity.getStuId(),
                entity.getClassName(), entity.getSubName(), entity.getMonth(), entity.getDate(), entity.getTime(),
                entity.getType());
    }

    @Override
    public boolean delete(String attId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Attendance WHERE Att_id = ?", attId);
    }

    @Override
    public boolean update(Attendance entity) throws SQLException {
        return SQLUtil.execute("UPDATE Attendance SET Stu_id = ?, Stu_Class = ?, Subject = ?, Month = ?, date = ?, " +
                "time = ?, type = ? WHERE Att_id = ?", entity.getStuId(), entity.getClassName(), entity.getSubName(),
                entity.getMonth(), entity.getDate(), entity.getTime(), entity.getType());
    }

    @Override
    public Attendance search(String attId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Attendance WHERE Att_id = ?", attId);

        resultSet.next();

        return new Attendance(resultSet.getString(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                resultSet.getDate(5).toLocalDate(), resultSet.getTime(6).toLocalTime(),
                resultSet.getString(7));

    }

    @Override
    public ArrayList<Attendance> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Attendance");

        ArrayList<Attendance> attendanceList = new ArrayList<>();

        while (resultSet.next()){
            attendanceList.add(new Attendance (
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDate(6).toLocalDate(),
                    resultSet.getTime(7).toLocalTime(),
                    resultSet.getString(8)
            ));
        }
        return  attendanceList;
    }

    @Override
    public String getAllAttendant(String stu_id, String subject, String month, String stu_class) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(Att_id) FROM Attendance WHERE Stu_id = ? AND Subject = ? " +
                "AND Month = ? AND Stu_Class = ?" , stu_id, subject, month, stu_class);

        String Count = null;

        if (resultSet.next()) {
            Count = resultSet.getString(1);
        }
        return Count;
    }
}
