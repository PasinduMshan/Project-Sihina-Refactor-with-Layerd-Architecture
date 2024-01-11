package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SearchPaymentBO extends SuperBO {
    ArrayList<PaymentDto> searchStuPays(String id, String Month) throws SQLException;
    StudentDto searchStu(String id) throws SQLException;
}
