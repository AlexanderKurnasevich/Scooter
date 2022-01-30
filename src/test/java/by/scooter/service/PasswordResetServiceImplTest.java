package by.scooter.service;

import by.scooter.api.dao.PasswordResetTokenDAO;
import by.scooter.api.dao.UserDAO;
import by.scooter.api.sevice.MailService;
import by.scooter.api.sevice.PasswordResetService;
import by.scooter.dto.mail.AbstractEmailContext;
import by.scooter.entity.user.PasswordResetToken;
import by.scooter.entity.user.User;
import by.scooter.exception.TokenNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;
import javax.persistence.NoResultException;

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
        token.setId(1L);
        User user = new User();

        when(userDAO.findByEmail("email")).thenReturn(user);
        when(tokenDAO.getByUser(any(User.class))).thenReturn(token);
        doNothing().when(tokenDAO).delete(anyLong());
        when(tokenDAO.save(any(PasswordResetToken.class))).thenReturn(token);
        doNothing().when(mailService).send(any(AbstractEmailContext.class));

        service.generateResetToken("email");
        verify(userDAO, times(1)).findByEmail("email");
        verify(tokenDAO, times(1)).save(any(PasswordResetToken.class));
        verify(tokenDAO, times(1)).getByUser(any(User.class));
        verify(tokenDAO, times(1)).delete(anyLong());
        verify(mailService, times(1)).send(any(AbstractEmailContext.class));
    }

    @Test
    void generateResetToken_CatchTokenNotFoundException() throws MessagingException {
        PasswordResetToken token = new PasswordResetToken();
        token.setId(1L);
        User user = new User();

        when(userDAO.findByEmail("email")).thenReturn(user);
        when(tokenDAO.getByUser(any(User.class)))
                .thenThrow(new TokenNotFoundException("message", new NoResultException()));
        doNothing().when(tokenDAO).delete(anyLong());
        when(tokenDAO.save(any(PasswordResetToken.class))).thenReturn(token);
        doNothing().when(mailService).send(any(AbstractEmailContext.class));

        service.generateResetToken("email");
        verify(userDAO, times(1)).findByEmail("email");
        verify(tokenDAO, times(1)).save(any(PasswordResetToken.class));
        verify(tokenDAO, times(1)).getByUser(any(User.class));
        verify(tokenDAO, times(0)).delete(anyLong());
        verify(mailService, times(1)).send(any(AbstractEmailContext.class));
    }
}