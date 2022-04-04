package com.bank.authenticationserver.bankauthentication.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
