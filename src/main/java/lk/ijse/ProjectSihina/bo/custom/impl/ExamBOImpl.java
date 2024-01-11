package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.ExamBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.ClassDAO;
import lk.ijse.ProjectSihina.dao.custom.ExamDAO;
import lk.ijse.ProjectSihina.dao.custom.QueryDAO;
import lk.ijse.ProjectSihina.dao.custom.SubjectDAO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.ExamDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.entity.Exam;
import lk.ijse.ProjectSihina.entity.Query;
import lk.ijse.ProjectSihina.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExamBOImpl implements ExamBO {
    ExamDAO examDAO = (ExamDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EXAM);
    SubjectDAO subjectDAO = (SubjectDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUBJECT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);
    ClassDAO classDAO = (ClassDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CLASS);

    @Override
    public String generateExamId() throws SQLException {
        return examDAO.generateId();
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
    public ArrayList<ExamDto> getAllExam() throws SQLException {
        ArrayList<ExamDto> examDtos = new ArrayList<>();

        ArrayList<Exam> exams = examDAO.getAll();

        for (Exam exam : exams) {
            examDtos.add(new ExamDto(
                    exam.getExamId(),
                    classDAO.getClassName(exam.getClassID()),
                    subjectDAO.getSubjectName(exam.getSubjectID()),
                    exam.getDescription(),
                    exam.getDate(),
                    exam.getStartTime(),
                    exam.getEndTime()
            ));
        }
        return examDtos;
    }

    @Override
    public boolean AddExam(ExamDto dto) throws SQLException {
        return examDAO.save(new Exam(
                dto.getExamId(),
                dto.getDate(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getDescription(),
                classDAO.getClassID(dto.getClassName()),
                subjectDAO.getSubjectID(dto.getSubject())
        ));
    }

    @Override
    public boolean deleteExam(String examId) throws SQLException {
        return examDAO.delete(examId);
    }

    @Override
    public ExamDto SearchExam(String examId) throws SQLException {
        Exam exam = examDAO.search(examId);

        return new ExamDto(
                exam.getExamId(),
                classDAO.getClassName(exam.getClassID()),
                subjectDAO.getSubjectName(exam.getSubjectID()),
                exam.getDescription(),
                exam.getDate(),
                exam.getStartTime(),
                exam.getEndTime()
        );

    }

    @Override
    public boolean updateExam(ExamDto dto) throws SQLException {
        return examDAO.update(new Exam(
                dto.getExamId(),
                dto.getDate(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getDescription(),
                classDAO.getClassID(dto.getClassName()),
                subjectDAO.getSubjectID(dto.getSubject())
        ));
    }
}
