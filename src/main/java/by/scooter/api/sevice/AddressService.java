package by.scooter.api.sevice;

import by.scooter.dto.location.AddressDTO;
import by.scooter.dto.location.RentPointDTO;
import by.scooter.entity.location.Address;

public interface AddressService {

    AddressDTO saveRentPointAddress(RentPointDTO dto);

    Address getById(Long addressId);

    void updateAddress(Long updatedId, RentPointDTO update);
}
