package by.scooter.entity.dto;

import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResetPasswordMailContext {

    private final User user;
    private final PasswordResetToken token;
}
