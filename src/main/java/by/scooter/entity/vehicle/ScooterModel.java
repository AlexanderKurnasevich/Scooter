package by.scooter.entity.vehicle;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.enumerator.VehicleType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name="scooter_models",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"maker", "model"})
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
}
