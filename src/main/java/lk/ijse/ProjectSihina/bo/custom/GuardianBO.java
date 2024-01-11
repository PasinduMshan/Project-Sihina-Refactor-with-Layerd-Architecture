package lk.ijse.ProjectSihina.bo.custom;

import lk.ijse.ProjectSihina.bo.SuperBO;
import lk.ijse.ProjectSihina.dto.GuardianDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GuardianBO extends SuperBO {
    String getGuardianId() throws SQLException;
    boolean deleteGuard(String id) throws SQLException;
    GuardianDto SearchGuardianFromContact(String contact) throws SQLException;
    GuardianDto SearchGuardianFromId(String id) throws SQLException;
    GuardianDto SearchGuardianFromStuId(String stuId) throws SQLException;
    boolean updateGuardian(GuardianDto dto) throws SQLException;
    ArrayList<GuardianDto> getAllGuardian() throws SQLException;



}
