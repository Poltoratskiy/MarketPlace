package ru.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.marketplace.entity.Mail;
import ru.marketplace.entity.User;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(Mail mail) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getContent());
        helper.setTo(mail.getTo());
        helper.setFrom("system.developer.rus@gmail.com");
        if(mail.getAttachment() != null){
            String filename = mail.getAttachment().split("/")[mail.getAttachment().split("/").length - 1].replaceFirst("[.][^.]+$", "");
            helper.addAttachment(filename, new ClassPathResource(mail.getAttachment()));
        }
        emailSender.send(message);
    }


}
