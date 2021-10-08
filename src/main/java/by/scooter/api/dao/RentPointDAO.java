package by.scooter.api.dao;

import by.scooter.entity.dto.RentPointFilterDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;

import java.util.List;

public interface RentPointDAO extends DAO<RentPoint> {
    List<RentPoint> getAll(RentPointFilterDTO filter, Integer page, Integer size);

    List<Scooter> getScooters(Long pointId, Integer page, Integer size);
}
