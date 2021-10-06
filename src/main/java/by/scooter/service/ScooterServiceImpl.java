package by.scooter.service;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.api.sevice.ScooterService;
import by.scooter.entity.vehicle.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScooterServiceImpl implements ScooterService {

    private final ScooterDAO scooterDAO;

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
    public Scooter addScooter(Scooter scooter) {
        return scooterDAO.save(scooter);
    }

    @Override
    public void removeScooter(Long id) {
        scooterDAO.delete(id);
    }

    @Override
    public void updateScooter(Long updatedId, Scooter update) {
        Scooter updated = scooterDAO.getById(updatedId);
        Optional.of(update.getChargePercent()).ifPresent(updated::setChargePercent);
        Optional.of(update.getHomePoint()).ifPresent(updated::setHomePoint);
        Optional.of(update.getOdometer()).ifPresent(updated::setOdometer);
        Optional.of(update.getModel()).ifPresent(updated::setModel);
        scooterDAO.update(updated);
    }
}
