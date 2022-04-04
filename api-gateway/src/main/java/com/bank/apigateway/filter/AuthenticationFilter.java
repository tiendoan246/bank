package com.bank.apigateway.filter;

import com.bank.apigateway.constant.SecurityConstants;
import com.bank.apigateway.security.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (!request.getHeaders().containsKey(SecurityConstants.JWT_HEADER)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        String token = request.getHeaders().getOrEmpty(SecurityConstants.JWT_HEADER).get(0);
        token = token.contains("Bearer ") ? token.replace("Bearer ", "") : token;

        Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);

        exchange.getRequest().mutate()
                .header("roles", String.valueOf(claims.get("roles")))
                .build();

        return chain.filter(exchange);
    }
}
