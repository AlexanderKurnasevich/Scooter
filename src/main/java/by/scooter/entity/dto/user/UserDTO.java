package by.scooter.entity.dto.user;

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
    private Set<RoleDTO> roles;
}
