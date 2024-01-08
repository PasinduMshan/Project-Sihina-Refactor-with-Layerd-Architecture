package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.QueryDAO;
import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.DashBordScheduleDto;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.entity.Payment;
import lk.ijse.ProjectSihina.entity.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Query> getAllClass() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT c.class_id, c.Name, COUNT(s.Stu_id) AS " +
                "student_count FROM Class c LEFT JOIN Registration r ON c.class_id = r.class_id LEFT JOIN Student s ON " +
                "r.Stu_id = s.Stu_id GROUP BY c.class_id, c.Name ORDER BY c.class_id;");

        ArrayList<Query> classList = new ArrayList<>();

        while (resultSet.next()) {
            classList.add(
                    new Query(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }
        return classList;
    }

    @Override
    public List<Query> getTodaySchedule(LocalDate date) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT DAYNAME(NOW()) AS current_day");
        resultSet.next();
        String day  = resultSet.getString(1);

        resultSet = SQLUtil.execute("SELECT S.Start_Time , S.End_Time , C.Name , Su.Sub_Name FROM Schedule S JOIN " +
                "Class C ON S.class_id = C.class_id JOIN Subject Su ON Su.Sub_id = S.Sub_id WHERE S.Class_day = ?", day);

        ArrayList<Query> queriesList = new ArrayList<>();

        String type = "Today Class";

        while (resultSet.next()) {
            queriesList.add(new Query(
                    resultSet.getTime(1).toLocalTime(),
                    resultSet.getTime(2).toLocalTime(),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    type
            ));
        }
        return queriesList;
    }

    @Override
    public List<Query> getTodayExams(LocalDate date) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT E.Start_time, E.End_time, C.Name, Su.Sub_Name FROM Exam E JOIN " +
                "Class C ON E.class_id = C.class_id JOIN Subject Su ON E.Sub_id = Su.Sub_id WHERE E.date = ?", date);

        ArrayList<Query> queriesList = new ArrayList<>();

        String type = "Exam";

        while (resultSet.next()) {
            queriesList.add(new Query(
                    resultSet.getTime(1).toLocalTime(),
                    resultSet.getTime(2).toLocalTime(),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    type
            ));
        }
        return queriesList;
    }


   /* @Override
    public Payment SearchPaymentId(String payId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT P.Pay_id,P.Stu_id, S.Name,P.Type," +
                "P.Stu_Class,P.Pay_Month,P.Subject,P.Amount,P.date,P.time FROM Payment P JOIN Student S ON P.Stu_id" +
                " = S.Stu_id WHERE Pay_id = ?");
        pstm.setString(1, payId);
        ResultSet resultSet = pstm.executeQuery();

        PaymentDto dto = null;

        if (resultSet.next()) {
            String PayID = resultSet.getString(1);
            String StuId = resultSet.getString(2);
            String name = resultSet.getString(3);
            String type = resultSet.getString(4);
            String StuClass = resultSet.getString(5);
            String PayMonth = resultSet.getString(6);
            String Subject = resultSet.getString(7);
            double Amount = resultSet.getDouble(8);
            LocalDate date = resultSet.getDate(9).toLocalDate();
            LocalTime time = resultSet.getTime(10).toLocalTime();

            dto = new PaymentDto(PayID,StuId,name,type,StuClass,PayMonth,Subject,Amount,date,time);
        }
        return dto;
    }*/

    /*public static List<PaymentDto> getAllPayment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT P.Pay_id , S.Name , P.Stu_Class , P.Subject , " +
                "P.Pay_Month , P.Amount FROM Payment P JOIN Student S ON P.Stu_id = S.Stu_id ORDER BY Pay_id DESC ");
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<PaymentDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(new PaymentDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6)
            ));
        }
        return dtoList;
    }*/
}
