package ru.marketplace.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.controller.form.AuthenticationRequest;
import ru.marketplace.entity.User;
import ru.marketplace.repository.UserRepository;
import ru.marketplace.security.jwt.JwtTokenProvider;
import ru.marketplace.service.UserService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;


@RestController
public class UserController {

    @Autowired
    private UserService userService;




}
