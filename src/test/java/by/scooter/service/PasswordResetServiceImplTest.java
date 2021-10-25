package by.scooter.service;

import by.scooter.api.dao.ClientDAO;
import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.api.dao.RoleDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.MailService;
import by.scooter.api.sevice.PasswordResetService;
import by.scooter.api.sevice.UserService;
import by.scooter.api.sevice.UtilService;
import by.scooter.dto.mail.AbstractEmailContext;
import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PasswordResetServiceImplTest {

    private PasswordResetService service;
    private PasswordResetTokenDAO tokenDAO;
    private MailService mailService;
    private UserDAO userDAO;

    @BeforeEach
    public void init() {
        tokenDAO = Mockito.mock(PasswordResetTokenDAO.class);
        mailService = Mockito.mock(MailService.class);
        userDAO = Mockito.mock(UserDAO.class);
        service = new PasswordResetServiceImpl(tokenDAO, mailService, userDAO);
    }


    @Test
    void generateResetToken() throws MessagingException {
        PasswordResetToken token = new PasswordResetToken();
        User user = new User();

        when(userDAO.findByEmail("email")).thenReturn(user);
        when(tokenDAO.save(any(PasswordResetToken.class))).thenReturn(token);
        doNothing().when(mailService).send(any(AbstractEmailContext.class));

        service.generateResetToken("email");
        verify(userDAO, times(1)).findByEmail("email");
        verify(tokenDAO, times(1)).save(any(PasswordResetToken.class));
        verify(mailService, times(1)).send(any(AbstractEmailContext.class));
    }
}