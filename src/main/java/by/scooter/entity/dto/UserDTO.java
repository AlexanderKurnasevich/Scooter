package by.scooter.entity.dto;

import by.scooter.entity.user.Role;
import lombok.*;

import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;
}
