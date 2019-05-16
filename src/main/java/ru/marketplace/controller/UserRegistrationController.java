package ru.marketplace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marketplace.controller.form.UserRecoveryRequest;
import ru.marketplace.controller.form.UserRegistrationRequest;
import ru.marketplace.entity.Mail;
import ru.marketplace.entity.User;
import ru.marketplace.entity.enums.GenderEnum;
import ru.marketplace.repository.UserRepository;
import ru.marketplace.service.MailSenderService;
import ru.marketplace.service.UserService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/registration")
@Api(value = "/registration", description = "Регистрация пользователя")
public class UserRegistrationController {


    @Autowired
    UserRepository users;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @ApiOperation(value = "Create new user")
    @PostMapping("/new")
    public ResponseEntity userRegistration(@RequestBody UserRegistrationRequest data) {

        try {
            this.users.save(User.builder()
                    .username(data.getUserName())
                    .password(bCryptPasswordEncoder.encode(data.getPassword()))
                    .roles(Arrays.asList("ROLE_USER"))
                    .email(data.getEmail())
                    .gender(GenderEnum.valueOf(data.getGender()))
                    .build()
            );
            String email = data.getEmail();
            Mail mail = new Mail();
            mail.setSubject("Create account");
            mail.setContent("Create account: \n Your password: 123456");
            mail.setTo(email);
            mailSenderService.sendMail(mail);
        } catch (MessagingException | IOException e) {
            System.out.println(e);
        }

        return ResponseEntity.ok("success");
    }

    //{bcrypt}$2a$10$cOyDz.R6IEZ2YmEFT4B4kuspF3d0HHqgAFxm/bOKc9dnqTBPMy4zm

    @ApiOperation(value = "Recovery password")
    @PostMapping("/recovery")
    public ResponseEntity refreshToken(@RequestBody UserRecoveryRequest data) {
        String email = data.getEmail();
        Mail mail = new Mail();
        mail.setSubject("Account recovery");
        String newPassword = generateCommonLangPassword();
        mail.setContent(String.format("Account recovery content: \n New password: %s", newPassword));
        mail.setTo(email);
        User user = this.users.findByEmail(data.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email " + data.getEmail() + "not found"));
        user.setPassword(newPassword);
        userService.save(user);

        try {
            mailSenderService.sendMail(mail);
        } catch (MessagingException | IOException e) {
            System.out.println(e);
        }


        return ResponseEntity.ok("success");
    }

    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

}
