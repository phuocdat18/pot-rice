package com.cg.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum ERole {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER"),
    ROLE_MANAGER("MANAGER"),
    ROLE_CUSTOMER("CUSTOMER");

    private final String value;

    ERole(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ERole parse(String value) {

    }
}