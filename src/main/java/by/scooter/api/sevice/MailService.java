package by.scooter.api.sevice;

import by.scooter.entity.dto.AbstractEmailContext;

import javax.mail.MessagingException;

public interface MailService {
    void send(AbstractEmailContext message) throws MessagingException;
}
