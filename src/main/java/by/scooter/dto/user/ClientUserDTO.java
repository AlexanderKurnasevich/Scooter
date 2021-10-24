package by.scooter.dto.user;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ClientUserDTO {

    private Long id;

    @Pattern(regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
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
