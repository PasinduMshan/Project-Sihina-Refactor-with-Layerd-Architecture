package lk.ijse.ProjectSihina.dao.custom;

import lk.ijse.ProjectSihina.dao.CrudDAO;
import lk.ijse.ProjectSihina.entity.Guardian;

import java.sql.SQLException;

public interface GuardianDAO extends CrudDAO<Guardian> {
    Guardian SearchGuardianFromContact(String contact) throws SQLException;

    Guardian SearchGuardianFromStuId(String stuId) throws SQLException;

}
