package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country extends AbstractEntity {

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Только буквы")
    private String country;
}
