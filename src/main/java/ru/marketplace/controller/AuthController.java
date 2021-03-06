package ru.marketplace.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import ru.marketplace.controller.form.RefreshTokenRequest;
import ru.marketplace.repository.UserRepository;
import ru.marketplace.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.marketplace.controller.form.AuthenticationRequest;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/auth")
@Api(value = "/auth", description = "Операции с профилем пользователя")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository users;

    @ApiOperation(value = "Аутентификация пользователя")
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, dataType = "string", paramType = "header"),
    })
    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
            return ok(jwtTokenProvider.createTokenModel(this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getUsername(), token));

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @ApiOperation(value = "Обновление токена")
    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequest data) {

        try {
            String username = data.getUserName();
            if (jwtTokenProvider.validateToken(data.getRefreshTokenAccess())) {
                String token = jwtTokenProvider.createToken(username, this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getRoles());
                return ok(jwtTokenProvider.createTokenModel(this.users.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")).getUsername(), token));
            } else {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body("Expired or invalid JWT token");
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Expired or invalid JWT token");
        }

    }


}
