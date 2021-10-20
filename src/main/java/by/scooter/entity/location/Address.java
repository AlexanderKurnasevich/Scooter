package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(
        name = "addresses",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"street", "number", "postfix", "city_id"})
)
public class Address extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String street;

    @Column
    private Short number;

    @Column
    private String postfix;

    @ManyToOne
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return getStreet().equals(address.getStreet()) && getNumber().equals(address.getNumber()) && Objects.equals(getPostfix(), address.getPostfix()) && getCity().equals(address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStreet(), getNumber(), getPostfix(), getCity());
    }
}
