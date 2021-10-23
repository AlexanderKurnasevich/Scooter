package by.scooter.entity.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordDTO {

    @NotNull
    private String token;

    @NotNull
    private String email;

    @NotNull
    private String newPassword;
}
