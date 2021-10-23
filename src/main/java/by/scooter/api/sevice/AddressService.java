package by.scooter.api.sevice;

import by.scooter.entity.dto.location.AddressDTO;
import by.scooter.entity.dto.location.RentPointDTO;

public interface AddressService {

    AddressDTO saveRentPointAddress(RentPointDTO dto);
}
