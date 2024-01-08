package lk.ijse.ProjectSihina.dao.custom;

import javafx.scene.image.Image;
import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.entity.Teacher;

import java.sql.*;
import java.util.List;

public interface TeacherDAO extends CrudDAO<Teacher> {
    Blob convertImageToBlob(Image image);

    Image convertBlobToImage(Blob blob);

    List<Teacher> getAllNameTeacher() throws SQLException;

    String getTeacherId(String teacherName) throws SQLException;

    List<Teacher> getAllTeacherName() throws SQLException;

    String getTeacherName(String teacher_ID) throws SQLException;

    String getTeacherCount() throws SQLException;

    List<String> getAllEmail(String teacher) throws SQLException;

}
