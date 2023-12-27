package lk.ijse.ProjectSihina.model;

import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.AttendantDto;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendantModel {
    public static String generateNextAttId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT Att_id FROM Attendance ORDER BY Att_id DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return splitAttId(resultSet.getString(1));
        }
        return "At001";
    }

    private static String splitAttId(String currentId) {
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

    public static boolean AddAttendant(AttendantDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Attendance VALUES (?,?,?,?,?,?,?,?)");
        pstm.setString(1, dto.getAtt_id());
        pstm.setString(2, dto.getStudentId());
        pstm.setString(3, dto.getClassName());
        pstm.setString(4, dto.getSubject());
        pstm.setString(5, dto.getMonth());
        pstm.setDate(6, Date.valueOf(dto.getDate()));
        pstm.setTime(7, Time.valueOf(dto.getTime()));
        pstm.setString(8, dto.getType());
        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteAttendant(String attId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Attendance WHERE Att_id = ?");
        pstm.setString(1,attId);
        return pstm.executeUpdate() > 0;
    }

    public static boolean UpdateAttendent(AttendantDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Attendance SET Stu_id = ?, " +
                "Stu_Class = ?, Subject = ?, Month = ?, date = ?, time = ?, type = ? WHERE Att_id = ?");
        pstm.setString(1, dto.getStudentId());
        pstm.setString(2, dto.getClassName());
        pstm.setString(3, dto.getSubject());
        pstm.setString(4, dto.getMonth());
        pstm.setDate(5, Date.valueOf(dto.getDate()));
        pstm.setTime(6, Time.valueOf(dto.getTime()));
        pstm.setString(7, dto.getType());
        pstm.setString(8, dto.getAtt_id());
        return pstm.executeUpdate() > 0;
    }

    public static AttendantDto searchAttendant(String attId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Attendance WHERE Att_id = ?");
        pstm.setString(1,attId);
        ResultSet resultSet = pstm.executeQuery();
        
        AttendantDto dto = null;

        PreparedStatement pstm1 = connection.prepareStatement("SELECT Name FROM Student WHERE Stu_id = ?");

        if (resultSet.next()) {
            String AttId = resultSet.getString(1);
            String StuId = resultSet.getString(2);
            String StuClass = resultSet.getString(3);
            String subject = resultSet.getString(4);
            String month = resultSet.getString(5);
            LocalDate date = resultSet.getDate(6).toLocalDate();
            LocalTime time = resultSet.getTime(7).toLocalTime();
            String type = resultSet.getString(8);
            String StuName = null;

            pstm1.setString(1,StuId);
            ResultSet resultSet1 = pstm1.executeQuery();
            if (resultSet1.next()){
                 StuName = resultSet1.getString(1);
            }

            dto = new AttendantDto(AttId,StuId,StuName,StuClass,month,subject,date,time,type);
        }
        return dto;
    }

    public static List<AttendantDto> getAllAttendance() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Attendance");
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<AttendantDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(new AttendantDto(
                    resultSet.getString(1),
                    getStudentName(resultSet.getString(2)),
                    resultSet.getString(3),
                    resultSet.getString(5),
                    resultSet.getDate(6).toLocalDate(),
                    resultSet.getString(8)
            ));
        }
        return  dtoList;
    }
    private static String getStudentName(String StuId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT Name FROM Student WHERE Stu_id = ?");
        pstm.setString(1, StuId);
        ResultSet resultSet = pstm.executeQuery();
        String Name = null;
        if (resultSet.next()){
            Name = resultSet.getString(1);
        }
        return Name;
    }

    public static String searctStudent(String stuId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT Name FROM Student WHERE Stu_id = ?");
        pstm.setString(1, stuId);
        ResultSet resultSet = pstm.executeQuery();
        String StuName = null;
        if (resultSet.next()) {
            StuName = resultSet.getString(1);
        }
        return StuName;
    }
}