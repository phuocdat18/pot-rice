package com.cg.role.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RoleUpdateParam {
    @NotNull(message = "name not null")
    private String name;
}
