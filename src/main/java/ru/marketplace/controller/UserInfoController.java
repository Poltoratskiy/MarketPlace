package ru.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marketplace.entity.Mail;
import ru.marketplace.service.MailSenderService;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController()
public class UserInfoController {

    @Autowired
    private MailSenderService mailSenderService;


    @GetMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

    @GetMapping("/sendMail")
    public ResponseEntity sendMail() {

        Mail mail = new Mail();
        mail.setContent("Content");
        mail.setSubject("Subject");
        mail.setFrom("system.developer.rus@gmail.com");
        mail.setTo("system.developer.rus@gmail.com");
        mail.setAttachment("./statics/images/pingue.jpg");
        try {
            mailSenderService.sendMail(mail);
            return ok("ok");
        }
        catch (MessagingException| IOException s){
            return ResponseEntity.ok("false");
        }
    }


}
