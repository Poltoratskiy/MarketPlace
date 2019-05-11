package ru.marketplace.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.marketplace.entity.User;
import ru.marketplace.service.UserService;



@RestController
public class UserController {

    @Autowired
    private UserService userService;


}
