package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.GuardianDto;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegistrationBO extends SuperBO {
    ArrayList<PaymentDto> getAllRegisterPayment(String type) throws SQLException;
    ArrayList<ClassDto> getAllClass() throws SQLException;
    String generateNextPayId() throws SQLException;
    boolean DeletePayment(String payId) throws SQLException;
    boolean SaveStudentRegisterAndPayment(StudentDto studentDto, PaymentDto payDto, GuardianDto guardianDto) throws SQLException;
    PaymentDto SearchPaymentId(String payId) throws SQLException;
}
