package by.scooter.api.sevice;

import by.scooter.entity.dto.RentPointFilterDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;

import java.util.List;

public interface RentPointService {
    RentPoint getById(Long id);

    RentPoint addRentPoint(RentPoint rentPoint);

    void removeRentPoint(Long id);

    void updateRentPoint(Long updatedId, RentPoint update);

    List<RentPoint> getAll(RentPointFilterDTO filter, Integer page, Integer size);

    List<Scooter> scootersInRentPoint(Long id, Integer page, Integer size);
}
