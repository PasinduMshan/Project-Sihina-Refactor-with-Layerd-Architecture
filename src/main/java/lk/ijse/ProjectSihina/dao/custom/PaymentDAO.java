package lk.ijse.ProjectSihina.dao.custom;


import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.entity.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PaymentDAO extends CrudDAO<Payment> {

    ArrayList<Payment> searchStuPays(String id, String Month) throws SQLException;

    ArrayList<Payment> getAllRegisterPayment(String type) throws SQLException;

    String getSumOfAmount(LocalDate date) throws SQLException;

}
