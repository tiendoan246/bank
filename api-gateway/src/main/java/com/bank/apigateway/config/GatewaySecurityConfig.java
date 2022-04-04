package com.bank.apigateway.config;

import com.bank.apigateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class GatewaySecurityConfig {

	private final AuthenticationFilter authenticationFilter;

	@Autowired
	public GatewaySecurityConfig(AuthenticationFilter authenticationFilter) {
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/api/account/**")
						.filters(f -> f.filter(authenticationFilter).rewritePath("/api/account/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time",new Date().toString()))
						.uri("lb://ACCOUNT")).
						route(p -> p
								.path("/api/loan/**")
								.filters(f -> f.filter(authenticationFilter).rewritePath("/api/loan/(?<segment>.*)","/${segment}")
										.addResponseHeader("X-Response-Time",new Date().toString()))
								.uri("lb://LOAN")).
						route(p -> p
								.path("/api/card/**")
								.filters(f -> f.filter(authenticationFilter).rewritePath("/api/card/(?<segment>.*)","/${segment}")
										.addResponseHeader("X-Response-Time",new Date().toString()))
								.uri("lb://CARD")).
						route(p -> p
								.path("/api/auth/**")
								.filters(f -> f.rewritePath("/api/auth/(?<segment>.*)","/${segment}")
										.addResponseHeader("X-Response-Time",new Date().toString()))
								.uri("lb://BANK-AUTHENTICATION"))
				.build();
	}
}
