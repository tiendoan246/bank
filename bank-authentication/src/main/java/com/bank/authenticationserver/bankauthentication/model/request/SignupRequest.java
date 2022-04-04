package com.bank.authenticationserver.bankauthentication.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private LocalDate dateOfBirth;
    private Set<String> roles;
}
