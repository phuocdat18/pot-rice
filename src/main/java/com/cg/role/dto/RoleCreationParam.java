package com.cg.role.dto;

import com.cg.model.ERole;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class RoleCreationParam {
    @NotNull(message = "name not null")
    private String name;
    @NotNull(message = "name not null")
    private ERole code;
}
