package lk.ijse.ProjectSihina.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.ProjectSihina.bo.custom.RegistrationBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.*;
import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.GuardianDto;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationBOImpl implements RegistrationBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);
    GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.GUARDIAN);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    ClassDAO classDAO = (ClassDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CLASS);

    @Override
    public ArrayList<PaymentDto> getAllRegisterPayment(String type) throws SQLException {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        ArrayList<Payment> payments = paymentDAO.getAllRegisterPayment(type);

        for (Payment payment : payments) {
            paymentDtos.add(new PaymentDto (
                    payment.getPayID(),
                    payment.getStuID(),
                    studentDAO.getStudentName(payment.getStuID()),
                    payment.getStuClass(),
                    payment.getDate(),
                    payment.getAmount()
            ));
        }
        return paymentDtos;
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
    public String generateNextPayId() throws SQLException {
        return paymentDAO.generateId();
    }

    @Override
    public boolean DeletePayment(String payId) throws SQLException {
        return paymentDAO.delete(payId);
    }

    @Override
    public boolean SaveStudentRegisterAndPayment(StudentDto studentDto, PaymentDto payDto, GuardianDto guardianDto) throws SQLException {
        Connection connection = null;
        try {
            connection= DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSaved = studentDAO.save(new Student(studentDto.getID(), studentDto.getName(), studentDto.getEmail(),
                    studentDto.getAddress(), studentDto.getDob(), studentDto.getGender(), studentDto.getContact(),
                    studentDto.getStu_Class(), studentDto.getSubject(), studentDto.getStudentImage(), userDAO.getUserID()));

            if (isSaved) {
                System.out.println(1);
                boolean isAddGuard = guardianDAO.save(new Guardian(guardianDto.getGuardId(), guardianDto.getName(),
                        guardianDto.getContact(), guardianDto.getEmail(), guardianDto.getOccupation(), guardianDto.getStuId()));

                if (isAddGuard) {
                    System.out.println(2);
                    boolean isAddPayment = paymentDAO.save(new Payment(payDto.getPayID(), payDto.getStuID(), payDto.getType(),
                            payDto.getStuClass(), payDto.getSubject(), payDto.getPayMonth(), payDto.getDate(), payDto.getTime(),
                            payDto.getAmount()));

                    if (isAddPayment) {
                        System.out.println(3);
                        boolean isSaveRegistration = registrationDAO.save(new Registration(studentDto.getID(), payDto.getPayID(),
                                studentDto.getName(), classDAO.getClassID(studentDto.getStu_Class()), payDto.getAmount(),
                                payDto.getDate(), payDto.getTime()));

                        if (isSaveRegistration) {
                            System.out.println(4);
                            connection.commit();
                            return true;

                        } else {
                            connection.rollback();
                        }
                    } else {
                        connection.rollback();
                    }
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            if (connection != null) connection.rollback();
            throw new RuntimeException(e);
           // new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } finally {
            if (connection != null) connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public PaymentDto SearchPaymentId(String payId) throws SQLException {
        Payment payment = paymentDAO.search(payId);

        return new PaymentDto(payment.getPayID(), payment.getStuID(), studentDAO.getStudentName(payment.getStuID()),
                payment.getType(), payment.getStuClass(), payment.getPayMonth(), payment.getSubject(), payment.getAmount(),
                payment.getDate(), payment.getTime());
    }
}
