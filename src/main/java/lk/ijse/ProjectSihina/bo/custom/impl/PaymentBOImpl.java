package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.PaymentBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.*;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.entity.Payment;
import lk.ijse.ProjectSihina.entity.Query;
import lk.ijse.ProjectSihina.entity.Student;
import lk.ijse.ProjectSihina.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);

    @Override
    public ArrayList<SubjectDto> getAllSubject() throws SQLException {

        ArrayList<SubjectDto> subjectDtos = new ArrayList<>();

        ArrayList<Subject> subjects = subjectDAO.getAllSubjectName();

        for (Subject subject :  subjects) {
            subjectDtos.add(new SubjectDto(subject.getSubject()));
        }
        return subjectDtos;
    }

    @Override
    public ArrayList<ClassDto> getAllClass() throws SQLException {
        ArrayList<ClassDto> classDtos = new ArrayList<>();

        ArrayList<Query> queries = queryDAO.getAllClass();

        for (Query query : queries) {
            classDtos.add(new ClassDto(query.getClassID(), query.getClassName(), query.getStu_Count()));
        }
        return classDtos;
    }

    @Override
    public ArrayList<PaymentDto> getAllPayment() throws SQLException {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        ArrayList<Payment> payments = paymentDAO.getAll();

        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto(
                    payment.getPayID(),
                    studentDAO.getStudentName(payment.getStuID()),
                    payment.getStuClass(),
                    payment.getSubject(),
                    payment.getPayMonth(),
                    payment.getAmount()
            ));
        }
        return paymentDtos;
    }

    @Override
    public String generateNextPayId() throws SQLException {
        return paymentDAO.generateId();
    }

    @Override
    public boolean AddPayment(PaymentDto dto) throws SQLException {
        return paymentDAO.save(new Payment(dto.getPayID(), dto.getStuID(), dto.getType(), dto.getStuClass(), dto.getSubject(),
                dto.getPayMonth(), dto.getDate(), dto.getTime(), dto.getAmount()));
    }

    @Override
    public boolean DeletePayment(String payId) throws SQLException {
        return paymentDAO.delete(payId);
    }

    @Override
    public PaymentDto SearchPaymentId(String payId) throws SQLException {
        Payment payment = paymentDAO.search(payId);

        return new PaymentDto(payment.getPayID(), payment.getStuID(), studentDAO.getStudentName(payment.getStuID()),
                payment.getType(), payment.getStuClass(), payment.getPayMonth(), payment.getSubject(), payment.getAmount(),
                payment.getDate(), payment.getTime());
    }

    @Override
    public boolean updatePayment(PaymentDto dto) throws SQLException {
        return paymentDAO.update(new Payment(dto.getPayID(), dto.getStuID(), dto.getType(), dto.getStuClass(),
                dto.getSubject(), dto.getPayMonth(), dto.getDate(), dto.getTime(), dto.getAmount()));
    }

    @Override
    public String getAllAttendant(String stu_id, String subject, String month, String stu_class) throws SQLException {
        return attendanceDAO.getAllAttendant(stu_id, subject, month, stu_class);
    }

    @Override
    public double getAmountINSubject(String subject) throws SQLException {
        return subjectDAO.getAmountINSubject(subject);
    }

    @Override
    public StudentDto getStudentNameClass(String id) throws SQLException {
        Student student = studentDAO.getStudentNameClass(id);

        return new StudentDto(student.getName(), student.getStu_Class());
    }
}
