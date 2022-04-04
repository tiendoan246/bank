package com.bank.loan.bankloan.controller;

import com.bank.loan.bankloan.config.LoanServiceConfig;
import com.bank.loan.bankloan.model.response.ConfigurationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    private final LoanServiceConfig loanServiceConfig;

    @Autowired
    public ConfigurationController(LoanServiceConfig loanServiceConfig) {
        this.loanServiceConfig = loanServiceConfig;
    }

    @GetMapping("loans/configuration")
    public ResponseEntity<ConfigurationResponse> getConfigurations() {
        return new ResponseEntity<>(ConfigurationResponse.builder()
                .msg(loanServiceConfig.getMsg())
                .activeBranches(loanServiceConfig.getActiveBranches())
                .buildVersion(loanServiceConfig.getBuildVersion())
                .mailDetails(loanServiceConfig.getMailDetails())
                .build(), HttpStatus.OK);
    }
}
