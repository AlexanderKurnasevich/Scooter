package by.scooter.trash;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.enumerator.VehicleType;

public class VehicleModel extends AbstractEntity {
    private VehicleType type;
    private String maker;
    private String model;
    private Integer passengerCapacity;
    private Integer carryCapacity;
}
