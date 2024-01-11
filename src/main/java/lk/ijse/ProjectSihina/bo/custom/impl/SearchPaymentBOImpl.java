package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.SearchPaymentBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.PaymentDAO;
import lk.ijse.ProjectSihina.dao.custom.StudentDAO;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.entity.Payment;
import lk.ijse.ProjectSihina.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchPaymentBOImpl implements SearchPaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<PaymentDto> searchStuPays(String id, String Month) throws SQLException {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        ArrayList<Payment> payments = paymentDAO.searchStuPays(id,Month);

        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(payment.getPayID(), payment.getSubject(), payment.getAmount()));
        }
        return paymentDtos;
    }

    @Override
    public StudentDto searchStu(String id) throws SQLException {
        Student student = studentDAO.searchStu(id);

        return new StudentDto(student.getName(), student.getSubject());
    }
}
