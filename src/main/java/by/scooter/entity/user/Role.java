package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.enumerator.RoleValue;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleValue value;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return value.name();
    }
}
