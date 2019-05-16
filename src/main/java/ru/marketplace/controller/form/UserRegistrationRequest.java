package ru.marketplace.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest implements Serializable {
    private String userName;
    private String email;
    private String name;
    private String surname;
    private String gender;
    private String password;
}