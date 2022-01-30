package by.scooter.api.sevice;

import by.scooter.dto.location.RentPointDTO;
import by.scooter.dto.location.RentPointFilterDTO;
import by.scooter.dto.vehicle.ScooterDTO;

import java.util.List;

public interface RentPointService {

    RentPointDTO getById(Long id);

    RentPointDTO addRentPoint(RentPointDTO rentPoint);

    void removeRentPoint(Long id);

    void updateRentPoint(Long updatedId, RentPointDTO update);

    List<RentPointDTO> getAll(RentPointFilterDTO filter, Integer page, Integer size);

    List<ScooterDTO> scootersInRentPoint(Long id, Integer page, Integer size);
}
