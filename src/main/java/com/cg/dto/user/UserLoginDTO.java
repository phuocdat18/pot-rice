package com.cg.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserLoginDTO {
    @NotBlank(message = "The username is required")
//    @Length(min = 1, message = "The username is required")
    private String username;

    @NotBlank(message = "The password is required")
    private String password;
}
