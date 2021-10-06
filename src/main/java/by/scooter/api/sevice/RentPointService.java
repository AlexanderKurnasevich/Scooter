package by.scooter.api.sevice;

import by.scooter.entity.location.RentPoint;

public interface RentPointService {
    RentPoint addRentPoint(RentPoint rentPoint);

    void removeRentPoint(Long id);

    void updateRentPoint(Long updatedId, RentPoint update);
}
