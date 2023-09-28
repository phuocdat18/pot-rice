package com.cg.role.dto;

import com.cg.model.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Accessors(chain = true)
public class RoleResult {
    private Long id;
    private ERole code;
    private String name;
}
