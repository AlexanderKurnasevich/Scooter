package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country extends AbstractEntity {

    @Column(nullable = false, unique = true, name = "country_name")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String countryName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return getCountryName().equals(country.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCountryName());
    }
}
