package by.scooter.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){2,18}[a-zA-Z0-9]$",
            message = """
                    Username consists of alphanumeric characters (a-zA-Z0-9), lowercase, or uppercase.
                    Username allowed of the dot (.), underscore (_), and hyphen (-).
                    The dot (.), underscore (_), or hyphen (-) must not be the first or last character.
                    The dot (.), underscore (_), or hyphen (-) does not appear consecutively, e.g., user..name
                    The number of characters must be between 4 to 20.""")
    @NotNull
    private String username;

    @Min(value = 4)
    private String password;

    @Email
    @NotNull
    private String email;
    private Set<RoleDTO> roles;
}
