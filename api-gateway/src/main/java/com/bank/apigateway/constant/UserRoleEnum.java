package com.bank.apigateway.constant;

import java.util.stream.Stream;

public enum UserRoleEnum {

    ROLE_USER("ROLE_USER"),
    ROLE_OPERATOR("ROLE_OPERATOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    private String name;

    private UserRoleEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static UserRoleEnum from(final String name) {
        return Stream.of(UserRoleEnum.values()).filter(targetEnum -> targetEnum.name.equals(name)).findFirst().orElse(null);
    }
}
