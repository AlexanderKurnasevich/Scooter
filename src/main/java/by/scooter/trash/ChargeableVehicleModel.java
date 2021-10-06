package by.scooter.trash;

import lombok.Data;

@Data
public abstract class ChargeableVehicleModel extends VehicleModel {
    protected Float chargeCapacity;
    protected Integer range;
}
