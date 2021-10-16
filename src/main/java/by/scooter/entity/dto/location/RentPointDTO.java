package by.scooter.entity.dto.location;

import by.scooter.entity.dto.vehicle.ScooterDTO;
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
