package by.scooter.api.sevice;

import by.scooter.entity.vehicle.ScooterModel;

public interface ScooterModelService {
    ScooterModel addScooterModel(ScooterModel scooterModel);

    void removeScooterModel(Long id);

    void updateScooterModel(Long updatedId, ScooterModel update);
}
