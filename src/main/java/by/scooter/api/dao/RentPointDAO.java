package by.scooter.api.dao;

import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;

import java.util.List;

public interface RentPointDAO extends DAO<RentPoint>{
    List<Scooter> getScooters(Long pointId, Integer page, Integer size);
}
