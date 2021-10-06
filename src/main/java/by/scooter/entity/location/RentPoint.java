package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.vehicle.Scooter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rent_points")
public class RentPoint extends AbstractEntity {

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String country;

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String city;

    @Column(nullable = false)
    private String address;

    @ManyToMany
    private Set<Scooter> scooters;
}
