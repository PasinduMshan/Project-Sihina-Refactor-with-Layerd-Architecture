package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<SubjectDto> getAllSubject() throws SQLException;
    ArrayList<ClassDto> getAllClass() throws SQLException;
    ArrayList<PaymentDto> getAllPayment() throws SQLException;
    String generateNextPayId() throws SQLException;
    boolean AddPayment(PaymentDto dto) throws SQLException;
    boolean DeletePayment(String payId) throws SQLException;
    PaymentDto SearchPaymentId(String payId) throws SQLException;
    boolean updatePayment(PaymentDto dto) throws SQLException;
    String getAllAttendant(String stu_id, String subject, String month, String stu_class) throws SQLException;
    double getAmountINSubject(String subject) throws SQLException;
    StudentDto getStudentNameClass(String id) throws SQLException;
}
