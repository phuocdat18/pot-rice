package com.cg.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum ERole {
    ADMIN("ADMIN"),
    USER("USER"),
    MANAGER("MANAGER"),
    CUSTOMER("CUSTOMER");

    private final String value;

    ERole(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ERole parse(String value) {
        for (ERole role : ERole.values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid ERole value: " + value);
    }
}