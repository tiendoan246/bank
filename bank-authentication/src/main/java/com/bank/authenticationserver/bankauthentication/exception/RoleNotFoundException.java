package com.bank.authenticationserver.bankauthentication.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleNotFoundException extends RuntimeException {
    private String msg;
}
