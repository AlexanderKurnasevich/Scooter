package by.scooter.entity.vehicle;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.location.RentPoint;
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
@Table(name = "scooters")
public class Scooter extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private ScooterModel model;

    @ManyToOne
    private RentPoint homePoint;

    @Column(nullable = false, columnDefinition = "integer default 100")
    @Positive
    private Float chargePercent;

    @Column(nullable = false, columnDefinition = "integer default 0")
    @Positive
    private Integer odometer; //in meters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scooter)) return false;
        if (!super.equals(o)) return false;
        Scooter scooter = (Scooter) o;
        return getModel().equals(scooter.getModel())
                && getHomePoint().equals(scooter.getHomePoint())
                && getChargePercent().equals(scooter.getChargePercent())
                && getOdometer().equals(scooter.getOdometer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getHomePoint(), getChargePercent(), getOdometer());
    }
}
