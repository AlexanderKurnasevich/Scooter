package by.scooter.entity.dto.vehicle;

import by.scooter.entity.enumerator.VehicleType;
import lombok.Data;

@Data
public class ScooterModelDTO {

    private Long id;
    private VehicleType vehicleType;
    private String maker;
    private String model;
    private Float chargingTime;         //in hours
    private Short maxRange;             //in km
    private Short maxSpeed;             //in km/h
    private Integer passengerCapacity;
    private Integer maxLoad;            //in kg
}
