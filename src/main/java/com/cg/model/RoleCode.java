package com.cg.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum RoleCode {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER");

    private final String value;

    RoleCode(String value) {
        this.value = value;
    }

    @JsonCreator
    public static RoleCode parse(String value) {
        for (RoleCode role : RoleCode.values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid ERole value: " + value);
    }
}