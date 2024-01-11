package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.ScheduleBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.*;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.ScheduleDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.dto.TeacherDto;
import lk.ijse.ProjectSihina.entity.Query;
import lk.ijse.ProjectSihina.entity.Schedule;
import lk.ijse.ProjectSihina.entity.Subject;
import lk.ijse.ProjectSihina.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleBOImpl implements ScheduleBO {
    TeacherDAO teacherDAO = (TeacherDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.TEACHER);
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SCHEDULE);
    ClassDAO classDAO = (ClassDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CLASS);


    @Override
    public ArrayList<TeacherDto> getAllTeacher() throws SQLException {
        ArrayList<TeacherDto> teacherDtos = new ArrayList<>();

        ArrayList<Teacher> teachers = teacherDAO.getAllTeacherName();

        for (Teacher teacher : teachers) {
            teacherDtos.add(new TeacherDto(teacher.getName()));
        }
        return teacherDtos;
    }

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
    public ArrayList<ScheduleDto> getAllSchedule() throws SQLException {
        ArrayList<ScheduleDto> scheduleDtos = new ArrayList<>();

        ArrayList<Schedule> schedules = scheduleDAO.getAll();

        for (Schedule schedule : schedules) {
            scheduleDtos.add(new ScheduleDto(
                    classDAO.getClassName(schedule.getClass_ID()),
                    subjectDAO.getSubjectName(schedule.getSub_ID()),
                    teacherDAO.getTeacherName(schedule.getTeacher_ID()),
                    schedule.getDay(),
                    schedule.getStartTime(),
                    schedule.getEndTime()
            ));
        }
        return scheduleDtos;
    }

    @Override
    public boolean AddSchedule(ScheduleDto dto) throws SQLException {
        return scheduleDAO.save(new Schedule(
                classDAO.getClassID(dto.getStu_Class()),
                subjectDAO.getSubjectID(dto.getSubject()),
                teacherDAO.getTeacherId(dto.getTeacher()),
                dto.getDay(),
                dto.getStartTime(),
                dto.getEndTime()
        ));
    }

    @Override
    public boolean DeleteSchedule(ScheduleDto dto) throws SQLException {
        return scheduleDAO.deleteSchedule(new Schedule(
                classDAO.getClassID(dto.getStu_Class()),
                subjectDAO.getSubjectID(dto.getSubject()),
                teacherDAO.getTeacherId(dto.getTeacher()),
                dto.getDay(),
                dto.getStartTime(),
                dto.getEndTime()
        ));
    }
}
