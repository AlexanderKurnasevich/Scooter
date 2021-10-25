package by.scooter.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordDTO {

    @NotNull
    private String token;

    @Email
    @NotNull
    private String email;


    @NotNull
    private String newPassword;
}
