package com.cg.role.dto;

import com.cg.model.RoleCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
public class RoleCreationParam extends BaseRole {
    @NotNull(message = "name not null")
    private RoleCode id;

}
