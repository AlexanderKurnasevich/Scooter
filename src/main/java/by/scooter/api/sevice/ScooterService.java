package by.scooter.api.sevice;

import by.scooter.entity.dto.vehicle.ScooterDTO;
import by.scooter.entity.dto.vehicle.ScooterFilterDTO;

import java.util.List;

public interface ScooterService {
    ScooterDTO getById(Long id);

    List<ScooterDTO> getAll();

    List<ScooterDTO> getAll(Integer page, Integer size);

    List<ScooterDTO> getVacantScooters(ScooterFilterDTO filterDTO, Integer page, Integer size);

    ScooterDTO addScooter(ScooterDTO scooter);

    void removeScooter(Long id);

    void updateScooter(Long updatedId, ScooterDTO update);

    void addMileage(Long scooterId, Integer mileage);

    void moveScooter(Long scooterId, Long rentPointId);
}
