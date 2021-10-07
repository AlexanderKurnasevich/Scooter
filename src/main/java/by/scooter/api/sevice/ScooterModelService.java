package by.scooter.api.sevice;

import by.scooter.entity.vehicle.ScooterModel;

import java.util.List;

public interface ScooterModelService {
    ScooterModel getById(Long id);

    ScooterModel addScooterModel(ScooterModel scooterModel);

    void removeScooterModel(Long id);

    void updateScooterModel(Long updatedId, ScooterModel update);

    List<ScooterModel> getAll();

    List<ScooterModel> getAll(Integer page, Integer size);
}
