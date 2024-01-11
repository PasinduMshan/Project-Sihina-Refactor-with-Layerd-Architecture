package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.StudentBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.QueryDAO;
import lk.ijse.ProjectSihina.dao.custom.StudentDAO;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.entity.Query;
import lk.ijse.ProjectSihina.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CLASS);

    @Override
    public String generateStudentId() throws SQLException {
        return studentDAO.generateId();
    }

    @Override
    public ArrayList<StudentDto> getAllStudent() throws SQLException {
        ArrayList<StudentDto> studentDtos = new ArrayList<>();

        ArrayList<Student> students = studentDAO.getAll();

        for (Student student : students) {
            studentDtos.add(new StudentDto(student.getID(), student.getName(), student.getStu_Class(), student.getEmail(),
                    student.getContact()));
        }
        return studentDtos;
    }

    @Override
    public ArrayList<ClassDto> getAllClass() throws SQLException {
        ArrayList<ClassDto> classDtos = new ArrayList<>();

        ArrayList<Query> queries =  queryDAO.getAllClass();

        for (Query query : queries) {
            classDtos.add(new ClassDto(query.getClassID(), query.getClassName(), query.getStu_Count()));
        }
        return classDtos;
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException {
        return studentDAO.delete(id);
    }

    @Override
    public boolean updateStudent(StudentDto dto) throws SQLException {
        return studentDAO.update(new Student(dto.getID(), dto.getName(), dto.getEmail(), dto.getAddress(), dto.getDob(),
                dto.getGender(), dto.getContact(), dto.getStu_Class(), dto.getSubject(), dto.getStudentImage()));
    }

    @Override
    public StudentDto searchStudent(String Id) throws SQLException {
        Student student = studentDAO.search(Id);

        return new StudentDto(student.getID(), student.getName(), student.getAddress(), student.getGender(), student.getEmail(),
                student.getDob(), student.getContact(), student.getStu_Class(), student.getSubject(), student.getStudentImage());
    }
}
