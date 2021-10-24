package by.scooter.service;

import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.MailService;
import by.scooter.api.sevice.PasswordResetService;
import by.scooter.dto.mail.AbstractEmailContext;
import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final PasswordResetTokenDAO tokenDAO;
    private final MailService mailService;
    private final UserDAO userDAO;

    @Override
    @Transactional
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
            public <T> void init(T context) {
                User cntxt = (User) context;
                setTemplateLocation("mails/password_reset");
                setSubject("Password reset");
                setTo(cntxt.getEmail());
                put("username", cntxt.getUsername());
            }
        };

        emailContext.put("token", token.getToken());
        emailContext.init(user);

        mailService.send(emailContext);
    }
}
