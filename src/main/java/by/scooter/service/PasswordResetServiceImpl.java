package by.scooter.service;

import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.MailService;
import by.scooter.api.sevice.PasswordResetService;
import by.scooter.dto.mail.AbstractEmailContext;
import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.User;
import by.scooter.exception.ServiceException;
import by.scooter.exception.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
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
        try {
            tokenDAO.delete(tokenDAO.getByUser(user).getId());
            tokenDAO.save(token);
        } catch (TokenNotFoundException ignore) {
            tokenDAO.save(token);
        }
        sendResetMail(user, token);
    }


    private void sendResetMail(User user, PasswordResetToken token) {
        AbstractEmailContext emailContext = new AbstractEmailContext() {
            @Override
            public <T> void init(T context) {
                User cntxt = (User) context;
                setTemplateLocation("mails/password_reset");
                setSubject("Password reset");
                setTo(cntxt.getEmail());
                put("username", cntxt.getUsername());
                put("email", cntxt.getEmail());
            }
        };

        emailContext.put("token", token.getToken());
        emailContext.init(user);

        try {
            mailService.send(emailContext);
        } catch (MessagingException ex) {
            log.warn("Mail sending failed", ex);
            throw new ServiceException("Unable to send a mail");
        }
    }
}
