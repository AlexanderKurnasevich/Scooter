package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.vehicle.Scooter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rent_points")
public class RentPoint extends AbstractEntity {

    @OneToOne(fetch = FetchType.EAGER)
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Scooter> scooters;
}
