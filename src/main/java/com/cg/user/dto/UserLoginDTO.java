package com.cg.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserLoginDTO {
    @NotNull(message = "{validate.user.username.notBlank}")
    @Length(min = 5, max = 30, message = "The length of username must be between 5 and 20 characters")
    private String username;

    @NotNull(message = "{validate.user.password.notBlank}")
    @Length(min = 6, max = 30, message = "The length of password must be between 6 and 20 characters")
    private String password;
}
