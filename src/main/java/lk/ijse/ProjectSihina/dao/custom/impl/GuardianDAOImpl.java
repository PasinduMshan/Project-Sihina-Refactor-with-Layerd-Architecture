package lk.ijse.ProjectSihina.dao.custom.impl;

import lk.ijse.ProjectSihina.dao.SQLUtil;
import lk.ijse.ProjectSihina.dao.custom.GuardianDAO;
import lk.ijse.ProjectSihina.entity.Guardian;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuardianDAOImpl implements GuardianDAO {
    @Override
    public String generateId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT Guard_id FROM Guardian ORDER BY Guard_id DESC LIMIT 1");

        if (resultSet.next()) {
            return splitId(resultSet.getString(1));
        }
        return "G001";
    }

    @Override
    public String splitId(String currentId) {
        if (currentId != null) {
            String[]  strings = currentId.split("G0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "G00"+id;
            } else {
                if (length < 3) {
                    return "G0"+id;
                } else {
                    return "G"+id;
                }
            }
        }
        return "G001";
    }

    @Override
    public boolean save(Guardian entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Guardian VALUES (?,?,?,?,?,?)", entity.getGuardId(), entity.getName(),
                entity.getContact(), entity.getEmail(), entity.getOccupation(), entity.getStuId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Guardian WHERE Guard_id = ?", id);

    }

    @Override
    public Guardian SearchGuardianFromContact(String contact) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Guardian WHERE contactNo = ?", contact);

        Guardian guardian = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNO = resultSet.getString(3);
            String Email = resultSet.getString(4);
            String occupation = resultSet.getString(5);
            String Stu_id = resultSet.getString(6);
            guardian = new Guardian(id, name, contactNO, Email, occupation, Stu_id);
        }
        return guardian;
    }

    @Override
    public Guardian search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Guardian WHERE Guard_id = ?", id);

        Guardian guardian = null;

        if (resultSet.next()) {
            String ID = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNO = resultSet.getString(3);
            String Email = resultSet.getString(4);
            String occupation = resultSet.getString(5);
            String Stu_id = resultSet.getString(6);
            guardian = new Guardian(ID, name, contactNO, Email, occupation, Stu_id);
        }
        return guardian;
    }

    @Override
    public Guardian SearchGuardianFromStuId(String stuId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Guardian WHERE Stu_id = ?", stuId);

        Guardian guardian = null;

        if (resultSet.next()) {
            String ID = resultSet.getString(1);
            String name = resultSet.getString(2);
            String contactNO = resultSet.getString(3);
            String Email = resultSet.getString(4);
            String occupation = resultSet.getString(5);
            String Stu_id = resultSet.getString(6);
            guardian = new Guardian(ID, name, contactNO, Email, occupation, Stu_id);
        }

        return guardian;
    }

    @Override
    public boolean update(Guardian entity) throws SQLException {
        return SQLUtil.execute("UPDATE Guardian SET Guard_Name = ?, contactNo = ?, Email = ?, occupation = ?, " +
                "Stu_id = ? WHERE Guard_id = ?", entity.getName(), entity.getContact(), entity.getEmail(),
                entity.getOccupation(), entity.getStuId(), entity.getGuardId());
    }

    @Override
    public ArrayList<Guardian> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Guardian");

        ArrayList<Guardian> GuardianList = new ArrayList<>();

        while (resultSet.next()) {
            GuardianList.add(new Guardian(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return GuardianList;
    }
}
