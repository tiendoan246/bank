package com.bank.card.bankcard.controller;

import com.bank.card.bankcard.config.CardServiceConfig;
import com.bank.card.bankcard.model.respose.ConfigurationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {

    private final CardServiceConfig cardServiceConfig;

    @Autowired
    public ConfigurationController(CardServiceConfig cardServiceConfig) {
        this.cardServiceConfig = cardServiceConfig;
    }

    @GetMapping("cards/configuration")
    public ResponseEntity<ConfigurationResponse> getConfigurations() {
        return new ResponseEntity<>(ConfigurationResponse.builder()
                .msg(cardServiceConfig.getMsg())
                .activeBranches(cardServiceConfig.getActiveBranches())
                .buildVersion(cardServiceConfig.getBuildVersion())
                .mailDetails(cardServiceConfig.getMailDetails())
                .build(), HttpStatus.OK);
    }
}
