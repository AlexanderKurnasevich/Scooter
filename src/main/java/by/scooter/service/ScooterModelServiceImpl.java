package by.scooter.service;

import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.sevice.ScooterModelService;
import by.scooter.entity.vehicle.ScooterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScooterModelServiceImpl implements ScooterModelService {
    private final ScooterModelDAO scooterModelDAO;

    @Override
    public ScooterModel getById(Long id) {
        return scooterModelDAO.getById(id);
    }

    @Override
    public ScooterModel addScooterModel(ScooterModel scooterModel) {
        return scooterModelDAO.save(scooterModel);
    }

    @Override
    public void removeScooterModel(Long id) {
        scooterModelDAO.delete(id);
    }

    @Override
    public void updateScooterModel(Long updatedId, ScooterModel update) {
        ScooterModel updated = scooterModelDAO.getById(updatedId);
        Optional.ofNullable(update.getModel()).ifPresent(updated::setModel);
        Optional.ofNullable(update.getMaker()).ifPresent(updated::setMaker);
        Optional.ofNullable(update.getChargingTime()).ifPresent(updated::setChargingTime);
        Optional.ofNullable(update.getMaxLoad()).ifPresent(updated::setMaxLoad);
        Optional.ofNullable(update.getMaxRange()).ifPresent(updated::setMaxRange);
        Optional.ofNullable(update.getMaxSpeed()).ifPresent(updated::setMaxSpeed);
        Optional.ofNullable(update.getPassengerCapacity()).ifPresent(updated::setPassengerCapacity);
        Optional.ofNullable(update.getVehicleType()).ifPresent(updated::setVehicleType);
        scooterModelDAO.update(updated);
    }

    @Override
    public List<ScooterModel> getAll() {
        return scooterModelDAO.getAll();
    }

    @Override
    public List<ScooterModel> getAll(Integer page, Integer size) {
        return scooterModelDAO.getAll(page, size);
    }
}
