package by.scooter.service;

import by.scooter.api.dao.RentPointDAO;
import by.scooter.api.sevice.RentPointService;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentPointServiceImpl implements RentPointService {
    private final RentPointDAO rentPointDAO;

    @Override
    public RentPoint getById(Long id) {
        return rentPointDAO.getById(id);
    }

    @Override
    public RentPoint addRentPoint(RentPoint rentPoint) {
        return rentPointDAO.save(rentPoint);
    }

    @Override
    public void removeRentPoint(Long id) {
        rentPointDAO.delete(id);
    }

    @Override
    public void updateRentPoint(Long updatedId, RentPoint update) {
        RentPoint updated = rentPointDAO.getById(updatedId);
        Optional.ofNullable(update.getAddress()).ifPresent(updated::setAddress);
        Optional.ofNullable(update.getScooters()).ifPresent(updated::setScooters);
        Optional.ofNullable(update.getCity()).ifPresent(updated::setCity);
        Optional.ofNullable(update.getCountry()).ifPresent(updated::setCountry);
        rentPointDAO.update(updated);
    }

    @Override
    public List<RentPoint> getAll() {
        return rentPointDAO.getAll();
    }

    @Override
    public List<RentPoint> getAll(Integer page, Integer size) {
        return rentPointDAO.getAll(page, size);
    }

    @Override
    public List<Scooter> scootersInRentPoint(Long id, Integer page, Integer size) {
        return rentPointDAO.getScooters(id, null, null);
    }
}
