package lk.ijse.ProjectSihina.dao.custom;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lk.ijse.ProjectSihina.User.UserConnection;
import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.entity.Student;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    Image convertBlobToImage(Blob blob);

    Blob convertImageToBlob(Image image);

    String getStudentName(String StuId) throws SQLException;

    Student searchStu(String id) throws SQLException;

    Student getStudentNameClass(String id) throws SQLException;

    String getStudentCount() throws SQLException;

    ArrayList<String> getAllEmailsByClass(String stuClass) throws SQLException;

    ArrayList<String> getAllEmail(String student) throws SQLException;
}
