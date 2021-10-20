package by.scooter.entity.vehicle;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.enumerator.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "scooter_models",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"maker", "model"})
)
public class ScooterModel extends AbstractEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(nullable = false)
    private String maker;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    @Positive
    private Float chargingTime; //in hours

    @Column(nullable = false)
    @Positive
    private Short maxRange; //in km

    @Column(nullable = false)
    @Positive
    private Short maxSpeed; //in km/h

    @Column(nullable = false, columnDefinition = "integer default 1")
    @Positive
    private Integer passengerCapacity;

    @Positive
    private Integer maxLoad; //in kg

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScooterModel)) return false;
        if (!super.equals(o)) return false;
        ScooterModel that = (ScooterModel) o;
        return getVehicleType() == that.getVehicleType() && getMaker().equals(that.getMaker()) && getModel().equals(that.getModel()) && getChargingTime().equals(that.getChargingTime()) && getMaxRange().equals(that.getMaxRange()) && getMaxSpeed().equals(that.getMaxSpeed()) && getPassengerCapacity().equals(that.getPassengerCapacity()) && getMaxLoad().equals(that.getMaxLoad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVehicleType(), getMaker(), getModel(), getChargingTime(), getMaxRange(), getMaxSpeed(), getPassengerCapacity(), getMaxLoad());
    }
}
