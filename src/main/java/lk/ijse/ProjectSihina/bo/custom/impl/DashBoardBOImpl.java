package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.DashBoardBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.*;
import lk.ijse.ProjectSihina.dto.DashBordScheduleDto;
import lk.ijse.ProjectSihina.entity.Query;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DashBoardBOImpl implements DashBoardBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEACHER);
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);


    @Override
    public ArrayList<DashBordScheduleDto> getTodaySchedule(LocalDate date) throws SQLException {
        ArrayList<DashBordScheduleDto> dashBordScheduleDtos = new ArrayList<>();

        ArrayList<Query> queries = queryDAO.getTodaySchedule(date);

        for (Query query : queries) {
            dashBordScheduleDtos.add(new DashBordScheduleDto(query.getStartTime(), query.getEndTime(), query.getClassName(),
                    query.getSubject(), query.getType()));
        }
        return dashBordScheduleDtos;
    }

    @Override
    public ArrayList<DashBordScheduleDto> getTodayExams(LocalDate date) throws SQLException {
        ArrayList<DashBordScheduleDto> dashBordScheduleDtos = new ArrayList<>();

        ArrayList<Query> queries = queryDAO.getTodayExams(date);

        for (Query query : queries) {
            dashBordScheduleDtos.add(new DashBordScheduleDto(query.getStartTime(), query.getEndTime(), query.getClassName(),
                    query.getSubject(), query.getType()));
        }
        return dashBordScheduleDtos;
    }

    @Override
    public String getSubjectCount() throws SQLException {
        return subjectDAO.getSubjectCount();
    }

    @Override
    public String getTeacherCount() throws SQLException {
        return teacherDAO.getTeacherCount();
    }

    @Override
    public String getStudentCount() throws SQLException {
        return studentDAO.getStudentCount();
    }

    @Override
    public String getSumOfAmount(LocalDate date) throws SQLException {
        return paymentDAO.getSumOfAmount(date);
    }
}
