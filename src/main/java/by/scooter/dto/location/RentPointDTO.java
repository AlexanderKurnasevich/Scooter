package by.scooter.dto.location;

import by.scooter.dto.vehicle.ScooterDTO;
import lombok.Data;

import java.util.Set;

@Data
public class RentPointDTO {

    private Long id;
    private Long addressId;
    private String country;
    private String city;
    private String street;
    private Short number;
    private String postfix;
    private Set<ScooterDTO> scooters;
}
