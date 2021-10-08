package by.scooter.service;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.dao.ScooterModelDAO;
import by.scooter.api.sevice.ScooterService;
import by.scooter.entity.vehicle.Scooter;
import by.scooter.entity.vehicle.ScooterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScooterServiceImpl implements ScooterService {

    private final ScooterDAO scooterDAO;
    private final ScooterModelDAO scooterModelDAO;

    @Override
    public Scooter getById(Long id) {
        return scooterDAO.getById(id);
    }

    @Override
    public List<Scooter> getAll() {
        return scooterDAO.getAll();
    }

    @Override
    public List<Scooter> getAll(Integer page, Integer size) {
        return scooterDAO.getAll(page, size);
    }

    @Override
    @Transactional
    public Scooter addScooter(Scooter scooter) {
        return scooterDAO.save(scooter);
    }

    @Override
    @Transactional
    public void removeScooter(Long id) {
        scooterDAO.delete(id);
    }

    @Override
    @Transactional
    public void updateScooter(Long updatedId, Scooter update) {
        Scooter updated = scooterDAO.getById(updatedId);
        Optional.ofNullable(update.getChargePercent()).ifPresent(updated::setChargePercent);
        Optional.ofNullable(update.getHomePoint()).ifPresent(updated::setHomePoint);
        Optional.ofNullable(update.getOdometer()).ifPresent(updated::setOdometer);
        if(update.getModel() != null) {
            updated.setModel(scooterModelDAO.getById(update.getModel().getId()));
        }
        scooterDAO.update(updated);
    }
}
