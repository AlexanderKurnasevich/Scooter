package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(
        name = "cities",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"cityName", "country_id"})
)
public class City extends AbstractEntity {

    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String cityName;

    @ManyToOne
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        if (!super.equals(o)) return false;
        City city = (City) o;
        return getCityName().equals(city.getCityName()) && getCountry().equals(city.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCityName(), getCountry());
    }
}
