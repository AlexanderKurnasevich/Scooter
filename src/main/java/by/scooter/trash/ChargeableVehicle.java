package by.scooter.trash;

import lombok.Data;

@Data
public abstract class ChargeableVehicle extends Vehicle {
    protected Float chargePercent;
}
