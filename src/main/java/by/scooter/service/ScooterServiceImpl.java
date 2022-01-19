package by.scooter.service;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.sevice.ScooterService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.vehicle.ScooterDTO;
import by.scooter.dto.vehicle.ScooterFilterDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScooterServiceImpl implements ScooterService {

    private final ScooterDAO scooterDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Autowired
    public ScooterServiceImpl(ScooterDAO scooterDAO, ModelMapper mapper, UtilService utilService) {
        this.scooterDAO = scooterDAO;
        this.mapper = mapper;
        this.utilService = utilService;
    }

    @Override
    public ScooterDTO getById(Long id) {
        return mapper.map(scooterDAO.getById(id), ScooterDTO.class);
    }

    @Override
    public List<ScooterDTO> getAll() {
        return utilService.convertList(scooterDAO.getAll(), ScooterDTO.class);
    }

    @Override
    public List<ScooterDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(scooterDAO.getAll(page, size), ScooterDTO.class);
    }

    @Override
    public List<ScooterDTO> getVacantScooters(ScooterFilterDTO filterDTO, Integer page, Integer size) {
        return utilService.convertList(scooterDAO.getVacant(filterDTO, page, size), ScooterDTO.class);
    }

    @Override
    @Transactional
    public ScooterDTO addScooter(ScooterDTO scooter) {
        return mapper.map(scooterDAO.save(mapper.map(scooter, Scooter.class)), ScooterDTO.class);
    }

    @Override
    @Transactional
    public void removeScooter(Long id) {
        scooterDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateScooter(Long updatedId, ScooterDTO update) {
        update.setId(updatedId);
        scooterDAO.update(mapper.map(update, Scooter.class));
    }

    @Override
    @Transactional
    public void addMileage(Long scooterId, Integer mileage) {
        Scooter updated = scooterDAO.getById(scooterId);
        updated.setOdometer(updated.getOdometer() + mileage);
        scooterDAO.update(updated);
    }

    @Override
    @Transactional
    public void moveScooter(Long scooterId, Long rentPointId) {
        Scooter updated = scooterDAO.getById(scooterId);
        RentPoint rentPoint = new RentPoint();
        rentPoint.setId(rentPointId);
        updated.setCurrentPoint(rentPoint);
        scooterDAO.update(updated);
    }
}
