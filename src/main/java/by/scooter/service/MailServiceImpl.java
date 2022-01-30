package by.scooter.service;

import by.scooter.api.sevice.MailService;
import by.scooter.dto.mail.AbstractEmailContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
@PropertySource("classpath:application.properties")
public class MailServiceImpl implements MailService {

    private final JavaMailSender emailSender;
    private final ITemplateEngine templateEngine;

    @Value("${mail.from}")
    private String from;

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender, ITemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void send(AbstractEmailContext email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(email.getContext());
        String emailContent = templateEngine.process(email.getTemplateLocation(), context);

        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
    }
}
