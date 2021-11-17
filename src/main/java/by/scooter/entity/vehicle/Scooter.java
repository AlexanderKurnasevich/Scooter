package by.scooter.entity.vehicle;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.location.RentPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scooters")
public class Scooter extends AbstractEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private ScooterModel model;

    @ManyToOne
    private RentPoint currentPoint;

    @Column(nullable = false, columnDefinition = "integer default 0")
    @PositiveOrZero
    private Integer odometer = 0; //in meters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter)) return false;
        if (!super.equals(o)) return false;
        Scooter scooter = (Scooter) o;
        return getModel().equals(scooter.getModel()) && getOdometer().equals(scooter.getOdometer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getOdometer());
    }
}
