package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.SubjectDAO;
import lk.ijse.ProjectSihina.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO {

    @Override
    public boolean save(Subject entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Subject VALUES (?,?,?,?,?)", entity.getId(), entity.getSubject(),
                entity.getAvailableClass(), entity.getTeacherName(), entity.getMonthlyAmount());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Subject WHERE Sub_id = ?",id);
    }

    @Override
    public boolean update(Subject entity) throws SQLException {
        return SQLUtil.execute("UPDATE Subject SET Sub_Name = ?, AvailableClass = ?, teacherName = ?, MonthlyAmount " +
                "= ? WHERE Sub_id = ?", entity.getSubject(), entity.getAvailableClass(), entity.getTeacherName(),
                entity.getMonthlyAmount(), entity.getId());
    }

    @Override
    public Subject search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Subject WHERE Sub_id = ?", id);

        Subject entity = null;

        if (resultSet.next()) {
            String S_id = resultSet.getString(1);
            String S_Name = resultSet.getString(2);
            String AvailableClass = resultSet.getString(3);
            String Teacher = resultSet.getString(4);
            double Amount = Double.parseDouble(resultSet.getString(5));

            entity = new Subject(S_id, S_Name, AvailableClass, Teacher, Amount);
        }
        return entity;
    }

    @Override
    public ArrayList<Subject> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Subject");

        ArrayList<Subject> subjectsList = new ArrayList<>();

        while (resultSet.next()) {
            subjectsList.add(new Subject(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDouble(5)
            ));
        }
        return subjectsList;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Sub_id FROM Subject ORDER BY Sub_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "SUB001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("SUB0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "SUB00"+id;
            }else {
                if (length < 3){
                    return "SUB0"+id;
                }else {
                    return "SUB"+id;
                }
            }
        }
        return "SUB001";
    }

    @Override
    public double getAmountINSubject(String subject) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT MonthlyAmount FROM Subject WHERE Sub_Name = ?", subject);

        double amount = 0;

        if (resultSet.next()) {
            amount = resultSet.getDouble(1);
        }
        return amount;
    }

    @Override
    public String getSubjectID(String subject) throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT Sub_id FROM Subject WHERE Sub_Name = ?", subject);

        String Sub_Id = null;

        if (resultSet.next()) {
            Sub_Id = resultSet.getString(1);
        }
        return Sub_Id;
    }

    @Override
    public ArrayList<Subject> getAllSubjectName() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Sub_Name FROM Subject");

        ArrayList<Subject> subNameList = new ArrayList<>();

        while (resultSet.next()) {
            subNameList.add(new Subject(
                    resultSet.getString(1)
            ));
        }
        return subNameList;
    }

    @Override
    public String getSubjectName(String sub_Id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Sub_Name FROM Subject WHERE Sub_id = ?", sub_Id);

        String Subject = null;

        if (resultSet.next()) {
            Subject = resultSet.getString(1);
        }
        return Subject;
    }

    @Override
    public String getSubjectCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(Sub_id) FROM Subject");

        resultSet.next();

        return resultSet.getString(1);

    }

}
