package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.vehicle.Scooter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rent_points")
public class RentPoint extends AbstractEntity {

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "currentPoint")
    private Set<Scooter> scooters;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentPoint)) return false;
        if (!super.equals(o)) return false;
        RentPoint rentPoint = (RentPoint) o;
        return getAddress().equals(rentPoint.getAddress()) && getScooters().equals(rentPoint.getScooters());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), getScooters());
    }
}
