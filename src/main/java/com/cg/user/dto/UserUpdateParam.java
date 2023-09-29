package com.cg.user.dto;

import com.cg.model.User;
import com.cg.utils.ValidateUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserUpdateParam {
    @NotBlank(message = "The Full Name is required")
    @Size(min = 5, max = 50, message = "The length of Full Name must be between 5 and 50 characters")
    private String fullName;

    @NotBlank(message = "The email is required")
    @Email(message = "The email address is invalid")
    @Size(max = 100, message = "The length of email maximum 100 characters")
    private String email;

    @NotBlank(message = "The phone is required")
    @Pattern(regexp = "^(\\\\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$", message = "Invalid phone number")
    private String phone;
}
