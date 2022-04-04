package com.bank.account.bankaccount.controller;

import com.bank.account.bankaccount.config.AccountServiceConfig;
import com.bank.account.bankaccount.model.response.ConfigurationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    private final AccountServiceConfig accountServiceConfig;

    @Autowired
    public ConfigurationController(AccountServiceConfig accountServiceConfig) {
        this.accountServiceConfig = accountServiceConfig;
    }

    @GetMapping("accounts/configuration")
    public ResponseEntity<ConfigurationResponse> getConfigurations() {
        return new ResponseEntity<>(ConfigurationResponse.builder()
                .msg(accountServiceConfig.getMsg())
                .activeBranches(accountServiceConfig.getActiveBranches())
                .buildVersion(accountServiceConfig.getBuildVersion())
                .mailDetails(accountServiceConfig.getMailDetails())
                .build(), HttpStatus.OK);
    }
}
