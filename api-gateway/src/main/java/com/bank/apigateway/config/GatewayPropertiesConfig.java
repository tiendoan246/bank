package com.bank.apigateway.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class GatewayPropertiesConfig {
    @Value("${security.app.jwtSecret}")
    private String secret;
}
