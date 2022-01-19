package by.scooter.dto.vehicle;

import by.scooter.entity.enumerator.VehicleType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ScooterModelDTO {

    private Long id;

    @NotNull
    private VehicleType vehicleType;

    @NotNull
    private String maker;

    @NotNull
    private String model;

    @NotNull
    @Positive
    private Float chargingTime;             //in hours

    @NotNull
    @Positive
    private Short maxRange;                 //in km

    @NotNull
    @Positive
    private Short maxSpeed;                 //in km/h

    @Min(1)
    private Integer passengerCapacity = 1;

    @Positive
    private Integer maxLoad;                //in kg
}
