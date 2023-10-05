package com.cg.user.dto;

import com.cg.role.dto.RoleResult;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
public class UserCreationParam extends BaseUser {
    @NotNull(message = "{validate.user.username.notBlank}")
    @Length(min = 5, max = 30, message = "The length of username must be between 5 and 20 characters")
    private String username;

    @NotNull(message = "{validate.user.password.notBlank}")
    @Length(min = 6, max = 30, message = "The length of password must be between 6 and 20 characters")
    private String password;

}
