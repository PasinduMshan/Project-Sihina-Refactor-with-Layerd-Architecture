package lk.ijse.ProjectSihina.bo;

import lk.ijse.ProjectSihina.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        ATTENDANCE, CHANGE_CREDENTIALS, CLASS, DASHBOARD, EMAIL, EXAM, FORGET_PASSWORD, GUARDIAN, LOGIN, PAYMENT, REGISTRATION,
        SCHEDULE, SEARCH_PAYMENT, SIGNUP, STUDENT,SUBJECT, TEACHER, USER
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case CHANGE_CREDENTIALS:
                return new ChangeCredentialsBOImpl();
            case CLASS:
                return new ClassBOImpl();
            case DASHBOARD:
                return new DashBoardBOImpl();
            case EMAIL:
                return new EmailBOImpl();
            case EXAM:
                return new ExamBOImpl();
            case FORGET_PASSWORD:
                return new ForgetPasswordBOImpl();
            case GUARDIAN:
                return new GuardianBOImpl();
            case LOGIN:
                return new LoginBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case REGISTRATION:
                return new RegistrationBOImpl();
            case SCHEDULE:
                return new ScheduleBOImpl();
            case SEARCH_PAYMENT:
                return new SearchPaymentBOImpl();
            case SIGNUP:
                return new SignupBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case SUBJECT:
                return new SubjectBOImpl();
            case TEACHER:
                return new TeacherBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
