package by.scooter.api.sevice;

import by.scooter.entity.vehicle.Scooter;

import java.util.List;

public interface ScooterService {
    Scooter getById(Long id);

    List<Scooter> getAll();

    List<Scooter> getAll(Integer page, Integer size);

    Scooter addScooter(Scooter scooter);

    void removeScooter(Long id);

    void updateScooter(Long updatedId, Scooter update);
}
