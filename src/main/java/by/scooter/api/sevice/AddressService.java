package by.scooter.api.sevice;

import by.scooter.dto.location.AddressDTO;
import by.scooter.dto.location.RentPointDTO;

public interface AddressService {

    AddressDTO saveRentPointAddress(RentPointDTO dto);
}
