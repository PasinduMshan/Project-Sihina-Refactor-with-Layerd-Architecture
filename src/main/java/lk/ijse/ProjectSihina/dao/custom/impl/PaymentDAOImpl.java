package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.PaymentDAO;
import lk.ijse.ProjectSihina.entity.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> searchStuPays(String id, String Month) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Pay_id, Subject , Amount  FROM Payment WHERE Stu_id = ? AND " +
                "Pay_Month = ?", id, Month);

        ArrayList<Payment> paymentsList = new ArrayList<>();

        while (resultSet.next()) {
            paymentsList.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            ));
            System.out.println(resultSet.getString(2));
        }
        return paymentsList;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Pay_id FROM Payment ORDER BY Pay_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "P001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("P0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "P00"+id;
            }else {
                if (length < 3){
                    return "P0"+id;
                }else {
                    return "P"+id;
                }
            }
        }
        return "P001";
    }

    @Override
    public boolean save(Payment entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Payment VALUES (?,?,?,?,?,?,?,?,?)", entity.getPayID(), entity.getStuID(),
                entity.getType(), entity.getStuClass(), entity.getSubject(), entity.getPayMonth(), entity.getDate(),
                entity.getTime(), entity.getAmount());
    }

    @Override
    public boolean delete(String payId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Payment WHERE Pay_id = ?", payId);
    }

    @Override
    public Payment search(String payId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Payment WHERE Pay_id = ?", payId);

        resultSet.next();

        return new Payment(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                resultSet.getDate(7).toLocalDate(), resultSet.getTime(8).toLocalTime(),
                resultSet.getDouble(9));
    }

    @Override
    public boolean update(Payment entity) throws SQLException {
        return SQLUtil.execute("UPDATE Payment SET Stu_id = ?, Type = ?, Stu_Class = ?, Subject = ?, Pay_Month = ?, " +
                "date = ?, time = ? , Amount = ? WHERE Pay_id = ?", entity.getStuID(), entity.getType(), entity.getStuClass(),
                entity.getSubject(), entity.getPayMonth(), entity.getDate(), entity.getTime(), entity.getAmount(),
                entity.getPayID());
    }

    @Override
    public ArrayList<Payment> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Pay_id, Stu_Class, Subject, Pay_Month, Amount FROM Payment");

        ArrayList<Payment> paymentsList = new ArrayList<>();

        while (resultSet.next()) {
            paymentsList.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }

        return paymentsList;
    }

    @Override
    public ArrayList<Payment> getAllRegisterPayment(String type) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Pay_id, Stu_id, Stu_Class, date, Amount FROM Payment WHERE " +
                "Type = ? ORDER BY Pay_id DESC", type);

        ArrayList<Payment> paymentsList = new ArrayList<>();

        while (resultSet.next()) {
            paymentsList.add(new Payment(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDouble(5)
            ));
        }

        return paymentsList;
    }

    @Override
    public String getSumOfAmount(LocalDate date) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(Amount) FROM Payment WHERE date = ?", date);

        resultSet.next();

        return resultSet.getString(1);
    }
}
