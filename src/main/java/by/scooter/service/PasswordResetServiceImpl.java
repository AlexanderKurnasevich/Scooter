package by.scooter.service;

import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.MailService;
import by.scooter.api.sevice.PasswordResetService;
import by.scooter.api.sevice.UserService;
import by.scooter.entity.dto.AbstractEmailContext;
import by.scooter.entity.dto.ResetPasswordMailContext;
import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenDAO tokenDAO;
    private final MailService mailService;
    private final UserService userService;
    private final UserDAO userDAO;

    @Override
    public void generateResetToken(String email) {
        PasswordResetToken token = new PasswordResetToken();
        User user = userDAO.findByEmail(email);
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        tokenDAO.save(token);
        sendResetMail(user, token);
    }

    @SneakyThrows
    private void sendResetMail(User user, PasswordResetToken token) {
        AbstractEmailContext emailContext = new AbstractEmailContext() {
            @Override
            protected <T> void init(T context) {
                ResetPasswordMailContext cntxt = (ResetPasswordMailContext) context;
                put("username", cntxt.getUser().getUsername());
                put("token", cntxt.getToken().getToken());
                setTemplateLocation("email/password_reset");
                setSubject("Password reset");
                setTo(cntxt.getUser().getEmail());
            }
        };
        mailService.send(emailContext);
    }
}
