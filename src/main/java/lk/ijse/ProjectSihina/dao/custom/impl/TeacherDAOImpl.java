package lk.ijse.ProjectSihina.dao.custom.impl;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.TeacherDAO;
import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.entity.Teacher;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements TeacherDAO {
    @Override
    public boolean save(Teacher entity) throws SQLException {
        Blob blob = convertImageToBlob(entity.getImageTeacher());

        return SQLUtil.execute("INSERT INTO Teacher VALUES (?,?,?,?,?,?,?)",entity.getId(),entity.getName(),
                entity.getAddress(),entity.getEmail(),entity.getContactNo(),entity.getSubjects(),blob);
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
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Teacher WHERE Teacher_id = ?", id);
    }

    @Override
    public boolean update(Teacher entity) throws SQLException {
        Blob blob = convertImageToBlob(entity.getImageTeacher());

        return SQLUtil.execute("UPDATE Teacher SET Name = ?, Address = ?, Email = ?, Contact = ?, Subject = ? ," +
                "image = ? WHERE Teacher_id = ?", entity.getName(),entity.getAddress(),entity.getEmail(),
                entity.getContactNo(),entity.getSubjects(),blob,entity.getId());
    }

    @Override
    public Teacher search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Teacher WHERE Teacher_id = ?",id);

        Teacher entity = null;

        if (resultSet.next()) {
            String T_id = resultSet.getString(1);
            String T_Name = resultSet.getString(2);
            String T_Address = resultSet.getString(3);
            String T_email = resultSet.getString(4);
            String T_Contact = resultSet.getString(5);
            String T_Subject = resultSet.getString(6);
            Image image = convertBlobToImage(resultSet.getBlob(7));

            entity = new Teacher(T_id,T_Name,T_Address,T_email,T_Subject,T_Contact,image);

        }
        return entity;
    }

    @Override
    public Image convertBlobToImage(Blob blob) {
        try {
            if (blob == null) {
                return null;
            }
            try (InputStream binaryStream = blob.getBinaryStream()){
                return new Image(binaryStream);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Teacher");

        ArrayList<Teacher> teachersList = new ArrayList<>();

        while (resultSet.next()) {
            teachersList.add(new Teacher(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    convertBlobToImage(resultSet.getBlob(6))
            ));
        }
        return teachersList;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Teacher_id FROM Teacher ORDER BY Teacher_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "TID001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("TID0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "TID00"+id;
            }else {
                if (length < 3){
                    return "TID0"+id;
                }else {
                    return "TID"+id;
                }
            }
        }
        return "TID001";
    }

    @Override
    public String getTeacherId(String teacher) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Teacher_id FROM Teacher WHERE Name = ?", teacher);
        String Teacher_id = null;
        if (resultSet.next()) {
            Teacher_id = resultSet.getString(1);
        }
        return Teacher_id;
    }

    @Override
    public ArrayList<Teacher> getAllTeacherName() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM Teacher");

        ArrayList<Teacher> teacherNameList = new ArrayList<>();

        while (resultSet.next()) {
            teacherNameList.add(new Teacher(
                    resultSet.getString(1)
            ));
        }
        return teacherNameList;
    }

    @Override
    public String getTeacherName(String teacher_ID) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM Teacher WHERE Teacher_id = ?", teacher_ID);

        String Teacher = null;

        if (resultSet.next()) {
            Teacher = resultSet.getString(1);
        }
        return Teacher;
    }

    @Override
    public String getTeacherCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(Teacher_id) FROM Teacher");

        resultSet.next();

        return resultSet.getString(1);
    }

    public ArrayList<String> getAllEmail(String teacher) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Email FROM Teacher");

        ArrayList<String> EmailList = new ArrayList<>();

        if (resultSet.next()){
            EmailList.add(resultSet.getString(1));
        }
        return EmailList;
    }
}
