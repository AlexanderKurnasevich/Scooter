package by.scooter.api.dao;

import by.scooter.entity.dto.vehicle.ScooterFilterDTO;
import by.scooter.entity.vehicle.Scooter;

import java.util.List;

public interface ScooterDAO extends DAO<Scooter> {

    List<Scooter> getVacant(ScooterFilterDTO filterDTO, Integer page, Integer size);
}
