package by.scooter.dto.location;

import by.scooter.dto.vehicle.ScooterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
