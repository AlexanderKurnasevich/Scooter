package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(
        name="cities",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"city", "country_id"})
)
public class City extends AbstractEntity {

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String cityName;

    @ManyToOne
    private Country country;
}
