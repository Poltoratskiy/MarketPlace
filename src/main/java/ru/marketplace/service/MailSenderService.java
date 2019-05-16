package ru.marketplace.service;

import org.springframework.transaction.annotation.Transactional;
import ru.marketplace.entity.Mail;
import ru.marketplace.entity.User;

import javax.mail.MessagingException;
import java.io.IOException;

@Transactional
public interface MailSenderService  {

    void sendMail (Mail mail) throws MessagingException, IOException;

}
