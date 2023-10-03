package com.cg.user.dto;

import com.cg.role.dto.RoleResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
public class UserResult {
    private Long id;

    @NotBlank(message = "The Full Name is required")
    @Size(min = 5, max = 50, message = "The length of Full Name must be between 5 and 50 characters")
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank(message = "The username is required")
    @Size(min = 5, max = 30, message = "The length of username must be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "The password is required")
    @Size(min = 6, max = 30, message = "The length of password must be between 6 and 20 characters")
    private String password;

    @NotBlank(message = "The email is required")
    @Email(message = "The email address is invalid")
    @Size(max = 100, message = "The length of email maximum 100 characters")
    private String email;

    @NotBlank(message = "The phone is required")
    @Pattern(regexp = "^(\\\\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$", message = "Invalid phone number")
    private String phone;

    private boolean deleted;

    private RoleResult roleResult;
    private Long roleId;
}
