package lk.ijse.ProjectSihina.bo.custom.impl;

import lk.ijse.ProjectSihina.bo.custom.GuardianBO;
import lk.ijse.ProjectSihina.dao.DAOFactory;
import lk.ijse.ProjectSihina.dao.custom.GuardianDAO;
import lk.ijse.ProjectSihina.dto.GuardianDto;
import lk.ijse.ProjectSihina.entity.Guardian;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuardianBOImpl implements GuardianBO {
    GuardianDAO guardianDAO = (GuardianDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.GUARDIAN);

    @Override
    public String getGuardianId() throws SQLException {
        return guardianDAO.generateId();
    }

    @Override
    public boolean deleteGuard(String id) throws SQLException {
        return guardianDAO.delete(id);
    }

    @Override
    public GuardianDto SearchGuardianFromContact(String contact) throws SQLException {
        Guardian guardian = guardianDAO.SearchGuardianFromContact(contact);

        return new GuardianDto(guardian.getGuardId(), guardian.getName(), guardian.getContact(), guardian.getEmail(),
                guardian.getOccupation(), guardian.getStuId());
    }

    @Override
    public GuardianDto SearchGuardianFromId(String id) throws SQLException {
        Guardian guardian = guardianDAO.search(id);

        return new GuardianDto(guardian.getGuardId(), guardian.getName(), guardian.getContact(), guardian.getEmail(),
                guardian.getOccupation(), guardian.getStuId());
    }

    @Override
    public GuardianDto SearchGuardianFromStuId(String stuId) throws SQLException {
        Guardian guardian = guardianDAO.SearchGuardianFromStuId(stuId);

        return new GuardianDto(guardian.getGuardId(), guardian.getName(), guardian.getContact(), guardian.getEmail(),
                guardian.getOccupation(), guardian.getStuId());
    }

    @Override
    public boolean updateGuardian(GuardianDto dto) throws SQLException {
        return guardianDAO.update(new Guardian(dto.getGuardId(), dto.getName(), dto.getContact(), dto.getEmail(),
                dto.getOccupation(), dto.getStuId()));
    }

    @Override
    public ArrayList<GuardianDto> getAllGuardian() throws SQLException {
        ArrayList<GuardianDto> guardianDtos = new ArrayList<>();

        ArrayList<Guardian> guardians = guardianDAO.getAll();

        for (Guardian guardian : guardians){
            guardianDtos.add(new GuardianDto(guardian.getGuardId(), guardian.getName(), guardian.getContact(),
                    guardian.getEmail(), guardian.getOccupation(), guardian.getStuId()));
        }
        return guardianDtos;
    }
}
