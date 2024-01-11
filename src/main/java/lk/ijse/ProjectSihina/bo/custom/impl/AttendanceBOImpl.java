package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.AttendanceBO;
import lk.ijse.ProjectSihina.bo.custom.DashBoardBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.*;
import lk.ijse.ProjectSihina.dto.AttendantDto;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.entity.Attendance;
import lk.ijse.ProjectSihina.entity.Query;
import lk.ijse.ProjectSihina.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);

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
    public ArrayList<AttendantDto> getAllAttendance() throws SQLException {
        ArrayList<AttendantDto> attendantDtos = new ArrayList<>();

        ArrayList<Attendance> attendances = attendanceDAO.getAll();

        for (Attendance attendance : attendances) {
            attendantDtos.add(new AttendantDto(
                    attendance.getAtt_id(),
                    studentDAO.getStudentName(attendance.getStuId()),
                    attendance.getClassName(),
                    attendance.getMonth(),
                    attendance.getDate(),
                    attendance.getType()
            ));
        }
        return attendantDtos;
    }

    @Override
    public boolean AddAttendant(AttendantDto dto) throws SQLException {
        return attendanceDAO.save(new Attendance(dto.getAtt_id(), dto.getStudentId(), dto.getClassName(), dto.getSubject(),
                dto.getMonth(), dto.getDate(), dto.getTime(), dto.getType()));
    }

    @Override
    public boolean deleteAttendant(String attId) throws SQLException {
        return attendanceDAO.delete(attId);
    }

    @Override
    public String generateNextAttId() throws SQLException {
        return attendanceDAO.generateId();
    }

    @Override
    public AttendantDto searchAttendant(String attId) throws SQLException {
        Attendance attendance = attendanceDAO.search(attId);

        return new AttendantDto(attendance.getAtt_id(), attendance.getStuId(), studentDAO.getStudentName(attendance.getStuId()),
                attendance.getClassName(), attendance.getMonth(), attendance.getSubName(), attendance.getDate(),
                attendance.getTime(), attendance.getType());
    }

    @Override
    public boolean UpdateAttendant(AttendantDto dto) throws SQLException {
        return attendanceDAO.update(new Attendance(dto.getAtt_id(), dto.getStudentId(), dto.getClassName(), dto.getSubject(),
                dto.getMonth(), dto.getDate(), dto.getTime(), dto.getType()));
    }

    @Override
    public String searchStudent(String stuId) throws SQLException {
        return studentDAO.getStudentName(stuId);
    }
}
