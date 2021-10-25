package by.scooter.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClientUserDTO {

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

    private Long userId;

    @Min(value = 4)
    private String password;

    @Size(min = 1, max = 30, message = "Name must be no shorter than 2 and no longer than 30 characters")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Letters only")
    @NotNull
    private String firstName;

    @Size(min = 1, max = 40, message = "Surname must be no shorter than 2 and no longer than 40 characters")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Letters only")
    @NotNull
    private String lastName;

    @Email(message = "Wrong email")
    @NotNull
    private String email;
}
