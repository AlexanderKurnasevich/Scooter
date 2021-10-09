package by.scooter.entity.dto.user;

import by.scooter.entity.user.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserInfoDTO {

    private Long id;
    private String username;
    //private Set<Role> roles;
}
