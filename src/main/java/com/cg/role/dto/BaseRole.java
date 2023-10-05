package com.cg.role.dto;

import com.cg.model.RoleCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
public class BaseRole {
    @NotNull(message = "name not null")
    private String name;
}
