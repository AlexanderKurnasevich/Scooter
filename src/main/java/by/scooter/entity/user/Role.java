package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.enumerator.RoleValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
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
}
