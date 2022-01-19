package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
public class Client extends AbstractEntity {

    @Size(min = 1, max = 30, message = "Name must be at least 2 and no longer than 30 characters")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Letters only")
    @NotNull
    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Size(min = 1, max = 40, message = "Surname must be at least 2 and no longer than 30 characters")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Letters only")
    @NotNull
    @Column(nullable = false, name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return getFirstName().equals(client.getFirstName()) && getLastName().equals(client.getLastName()) &&  getUser().equals(client.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFirstName(), getLastName(),  getUser());
    }
}
