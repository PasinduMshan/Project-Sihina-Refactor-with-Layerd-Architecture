package lk.ijse.ProjectSihina.dao.custom.impl;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.StudentDAO;
import lk.ijse.ProjectSihina.entity.Student;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Student VALUES (?,?,?,?,?,?,?,?,?,?,?)", entity.getID(), entity.getName(),
                entity.getEmail(), entity.getAddress(), entity.getDob(), entity.getGender(), entity.getContact(),
                entity.getStu_Class(), entity.getSubject(), convertImageToBlob(entity.getStudentImage()), entity.getUser());

    }

    @Override
    public Image convertBlobToImage(Blob blob) {
        try {
            if (blob == null) {
                return null;
            }
            try (InputStream binaryStream = blob.getBinaryStream()) {
                return new Image(binaryStream);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Student WHERE Stu_id = ?", id);
    }

    @Override
    public boolean update(Student entity) throws SQLException {
        return SQLUtil.execute("UPDATE Student SET Name = ?, Email = ?, Address = ?, D_O_B = ?, Gender = ?, Contact " +
                "= ?, Class = ?, subjects = ?, image = ? WHERE Stu_id = ?", entity.getName(), entity.getEmail(),
                entity.getAddress(), entity.getDob(), entity.getGender(), entity.getContact(), entity.getStu_Class(),
                entity.getSubject(), convertImageToBlob(entity.getStudentImage()), entity.getID());

    }

    @Override
    public Blob convertImageToBlob(Image image) {
        try {
            if (image == null) {
                return null;
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String format = "png";

            java.awt.image.BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

            ImageIO.write(bufferedImage, format, outputStream);

            byte[] imageBytes = outputStream.toByteArray();
            return new SerialBlob(imageBytes);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student search(String Id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Student WHERE Stu_id = ?", Id);

        resultSet.next();

        return new Student(resultSet.getString(1), resultSet.getString(2),
                resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5).toLocalDate(),
                resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
                resultSet.getString(9), convertBlobToImage(resultSet.getBlob(10)));

    }

    @Override
    public ArrayList<Student> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Stu_id,Name,Class,Email,Contact FROM Student");

        ArrayList<Student> studentsList = new ArrayList<>();

        while (resultSet.next()) {
            studentsList.add(
                    new Student(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return studentsList;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Stu_id FROM Student ORDER BY Stu_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "ID001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("ID0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "ID00"+id;
            }else {
                if (length < 3){
                    return "ID0"+id;
                }else {
                    return "ID"+id;
                }
            }
        }
        return "ID001";
    }

    @Override
    public String getStudentName(String StuId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM Student WHERE Stu_id = ?", StuId);
        String Name = null;
        if (resultSet.next()){
            Name = resultSet.getString(1);
        }
        return Name;
    }

    @Override
    public Student searchStu(String id) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Name , subjects  FROM Student WHERE Stu_id = ?");

        Student student = null;

        if (resultSet.next()) {
            String name = resultSet.getString(1);
            String subject = resultSet.getString(2);

            student = new Student(name,subject);

        }
        return student;
    }

    @Override
    public Student getStudentNameClass(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Name , Class FROM Student WHERE Stu_id = ?", id);

        Student student = null;

        if (resultSet.next()) {
            String name = resultSet.getString(1);
            String Class = resultSet.getString(2);

            student = new Student(id , name, Class);
        }
        return student;
    }

    @Override
    public String getStudentCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(Stu_id) FROM Student");

        resultSet.next();

        return resultSet.getString(1);
    }

    @Override
    public ArrayList<String> getAllEmailsByClass(String stuClass) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Email FROM Student WHERE Class = ?", stuClass);

        ArrayList<String> EmailList = new ArrayList<>();

        if (resultSet.next()){
            EmailList.add(resultSet.getString(1));
        }
        return EmailList;
    }

    @Override
    public ArrayList<String> getAllEmail(String Student) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Email FROM Student");

        ArrayList<String> EmailList = new ArrayList<>();

        if (resultSet.next()){
            EmailList.add(resultSet.getString(1));
        }
        return EmailList;
    }
}
