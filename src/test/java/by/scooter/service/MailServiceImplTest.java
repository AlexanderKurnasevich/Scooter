package by.scooter.service;

import by.scooter.api.sevice.MailService;
import by.scooter.dto.mail.AbstractEmailContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.*;

class MailServiceImplTest {

    private MailService mailService;
    private JavaMailSender emailSender;
    private ITemplateEngine templateEngine;

    @BeforeEach
    public void init() {
        emailSender = Mockito.mock(JavaMailSender.class);
        templateEngine = Mockito.mock(ITemplateEngine.class);
        mailService = new MailServiceImpl(emailSender, templateEngine);
    }

    @Test
    void send() throws MessagingException {
        ReflectionTestUtils.setField(mailService, "from", "any@from.com");
        AbstractEmailContext email = new AbstractEmailContext() {
            @Override
            public <T> void init(T context) {
                setTo("to");
                setSubject("sub");
                setTemplateLocation("template");
            }
        };
        email.init(null);

        MimeMessage message = new MimeMessage((Session) null);
        Context context = new Context();
        context.setVariables(email.getContext());

        when(templateEngine.process(eq("template"), any(IContext.class))).thenReturn("message");
        when(emailSender.createMimeMessage()).thenReturn(message);

        mailService.send(email);
        verify(emailSender, times(1)).send(any(MimeMessage.class));
    }
}