package by.scooter.service;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.sevice.ScooterService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.vehicle.ScooterDTO;
import by.scooter.entity.dto.vehicle.ScooterFilterDTO;
import by.scooter.entity.vehicle.Scooter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScooterServiceImpl implements ScooterService {

    private final ScooterDAO scooterDAO;
    private final ScooterModelDAO scooterModelDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

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
    public List<ScooterDTO> getAll(ScooterFilterDTO filterDTO, Integer page, Integer size) {
        return scooterDAO.getAll(filterDTO, page, size);
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
        Scooter updated = scooterDAO.getById(updatedId);
        Scooter src = mapper.map(update, Scooter.class);
        Optional.ofNullable(src.getOdometer()).ifPresent(updated::setOdometer);
        if(src.getModel() != null) {
            updated.setModel(scooterModelDAO.getById(src.getModel().getId()));
        }
        scooterDAO.update(updated);
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
        ScooterDTO scooterDTO = new ScooterDTO();
        scooterDTO.setCurrentPointId(rentPointId);
        updateScooter(scooterId, scooterDTO);
    }
}
