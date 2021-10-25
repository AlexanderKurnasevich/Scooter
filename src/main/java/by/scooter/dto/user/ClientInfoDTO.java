package by.scooter.dto.user;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ClientInfoDTO {

    private Long id;
    private String username;
    private Long userId;

    @Size(min = 1, max = 30, message = "Name must be no shorter than 2 and no longer than 30 characters")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Letters only")
    @Nullable
    private String firstName;

    @Size(min = 1, max = 40, message = "Surname must be no shorter than 2 and no longer than 40 characters")
    @Pattern(regexp = "^[A-Za-zА-Яа-я]*$", message = "Letters only")
    @Nullable
    private String lastName;

    @Email
    @Nullable
    private String email;
}
