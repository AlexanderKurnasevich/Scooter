package by.scooter.entity.user;

import by.scooter.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity implements UserDetails {

    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){2,18}[a-zA-Z0-9]$",
            message = """
                    Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
                    Username allowed of the dot (.), underscore (_), and hyphen (-).
                    The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
                    The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., user..name
                    The number of characters must be between 4 to 20.""")
    @NotNull
    private String username;

    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getRoles().equals(user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername(), getPassword(), getRoles());
    }
}
