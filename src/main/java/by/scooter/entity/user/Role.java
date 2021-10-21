package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.enumerator.RoleValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleValue value;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(RoleValue value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return value.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return getValue() == role.getValue() && getUsers().equals(role.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getValue(), getUsers());
    }
}
