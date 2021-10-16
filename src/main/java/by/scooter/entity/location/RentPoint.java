package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.vehicle.Scooter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
}
