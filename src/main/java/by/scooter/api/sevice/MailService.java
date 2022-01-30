package by.scooter.api.sevice;

import by.scooter.dto.mail.AbstractEmailContext;

import javax.mail.MessagingException;

public interface MailService {

    void send(AbstractEmailContext message) throws MessagingException;
}
