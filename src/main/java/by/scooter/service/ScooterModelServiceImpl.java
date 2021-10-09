package by.scooter.service;

import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.sevice.ScooterModelService;
import by.scooter.api.sevice.UtilService;
import by.scooter.entity.dto.vehicle.ScooterModelDTO;
import by.scooter.entity.vehicle.ScooterModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScooterModelServiceImpl implements ScooterModelService {
    private final ScooterModelDAO scooterModelDAO;
    private final ModelMapper mapper;
    private final UtilService utilService;

    @Override
    public ScooterModelDTO getById(Long id) {
        return mapper.map(scooterModelDAO.getById(id), ScooterModelDTO.class);
    }

    @Override
    public ScooterModelDTO addScooterModel(ScooterModelDTO scooterModel) {
        return mapper.map(scooterModelDAO.save(mapper.map(scooterModel, ScooterModel.class)), ScooterModelDTO.class);
    }

    @Override
    @Transactional
    public void removeScooterModel(Long id) {
        scooterModelDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateScooterModel(Long updatedId, ScooterModelDTO update) {
        ScooterModel updated = scooterModelDAO.getById(updatedId);
        ScooterModel scr = mapper.map(update, ScooterModel.class);
        Optional.ofNullable(scr.getModel()).ifPresent(updated::setModel);
        Optional.ofNullable(scr.getMaker()).ifPresent(updated::setMaker);
        Optional.ofNullable(scr.getChargingTime()).ifPresent(updated::setChargingTime);
        Optional.ofNullable(scr.getMaxLoad()).ifPresent(updated::setMaxLoad);
        Optional.ofNullable(scr.getMaxRange()).ifPresent(updated::setMaxRange);
        Optional.ofNullable(scr.getMaxSpeed()).ifPresent(updated::setMaxSpeed);
        Optional.ofNullable(scr.getPassengerCapacity()).ifPresent(updated::setPassengerCapacity);
        Optional.ofNullable(scr.getVehicleType()).ifPresent(updated::setVehicleType);
        scooterModelDAO.update(updated);
    }

    @Override
    public List<ScooterModelDTO> getAll() {
        return utilService.convertList(scooterModelDAO.getAll(), ScooterModelDTO.class);
    }

    @Override
    public List<ScooterModelDTO> getAll(Integer page, Integer size) {
        return utilService.convertList(scooterModelDAO.getAll(page, size), ScooterModelDTO.class);
    }
}
