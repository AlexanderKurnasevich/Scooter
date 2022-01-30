package by.scooter.api.sevice;

import by.scooter.dto.vehicle.ScooterModelDTO;

import java.util.List;

public interface ScooterModelService {

    ScooterModelDTO getById(Long id);

    ScooterModelDTO addScooterModel(ScooterModelDTO scooterModel);

    void removeScooterModel(Long id);

    void updateScooterModel(Long updatedId, ScooterModelDTO update);

    List<ScooterModelDTO> getAll();

    List<ScooterModelDTO> getAll(Integer page, Integer size);
}
