package ru.marketplace;

import lombok.Builder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.marketplace.entity.User;

import ru.marketplace.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {


    @Autowired
    UserRepository users;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Builder
    @Override
    public void run(String... args) throws Exception {
        this.users.save(User.builder()
                .username("user")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER"))
                .email("user@email.com")
                .build()
        );

        this.users.save(User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .email("admin@email.com")
                .build()
        );

        this.users.save(User.builder()
                .username("customer")
                .password(bCryptPasswordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_CUSTOMER"))
                .email("customer@email.com")
                .build()
        );
    }
}
