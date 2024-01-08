package lk.ijse.ProjectSihina.dao;

import lk.ijse.ProjectSihina.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        ATTENDANCE, CLASS, EXAM, GUARDIAN, PAYMENT, REGISTRATION, SCHEDULE, STUDENT, SUBJECT, TEACHER, USER, QUERY
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case ATTENDANCE:
                return new AttendanceDAOImpl();
            case CLASS:
                return new ClassDAOImpl();
            case EXAM:
                return new ExamDAOImpl();
            case GUARDIAN:
                return new GuardianDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case REGISTRATION:
                return new RegistrationDAOImpl();
            case SCHEDULE:
                return new ScheduleDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case SUBJECT:
                return new SubjectDAOImpl();
            case TEACHER:
                return new TeacherDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
