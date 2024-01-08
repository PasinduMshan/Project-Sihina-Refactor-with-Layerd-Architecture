package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.ExamDAO;
import lk.ijse.ProjectSihina.entity.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAOImpl implements ExamDAO {
    @Override
    public boolean save(Exam entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Exam VALUES (?,?,?,?,?,?,?)", entity.getExamId(), entity.getDate(),
                entity.getStartTime(), entity.getEndTime(), entity.getDescription(), entity.getClassID(), entity.getSubjectID());

    }

    @Override
    public boolean delete(String examId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Exam WHERE Exam_id = ?", examId);
    }

    @Override
    public Exam search(String examId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Exam WHERE Exam_id = ?", examId);

        resultSet.next();

        return new Exam(resultSet.getString(1), resultSet.getDate(2).toLocalDate(),
                resultSet.getTime(3).toLocalTime(), resultSet.getTime(4).toLocalTime(),
                resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));


    }

    @Override
    public boolean update(Exam entity) throws SQLException {

        return SQLUtil.execute("UPDATE Exam SET date = ? , Start_time = ? , End_time = ? , Description = ? , " +
                "class_id = ? , Sub_id = ? WHERE Exam_id = ?", entity.getDate(), entity.getStartTime(), entity.getEndTime(),
                entity.getDescription(), entity.getClassID(), entity.getSubjectID(), entity.getExamId());
    }

    @Override
    public List<Exam> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Exam");

        ArrayList<Exam> examList = new ArrayList<>();

        while (resultSet.next()) {
            examList.add(new Exam(
                    resultSet.getString(1),
                    resultSet.getDate(2).toLocalDate(),
                    resultSet.getTime(3).toLocalTime(),
                    resultSet.getTime(4).toLocalTime(),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return examList;
    }

    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Exam_id FROM Exam ORDER BY Exam_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "EX001";
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("EX0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "EX00"+id;
            }else {
                if (length < 3){
                    return "Ex0"+id;
                }else {
                    return "EX"+id;
                }
            }
        }
        return "EX001";
    }

}
