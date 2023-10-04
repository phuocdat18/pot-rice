package com.cg.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
public class UserUpdateParam {
    @NotNull(message = "{validate.user.fullName.notBlank}")
    @Length(min = 5, max = 50, message = "{validate.user.fullName.length}")
    private String fullName;

    @NotNull(message = "{validate.user.email.notBlank}")
    @Email(message = "The email address is invalid")
    @Length(max = 100, message = "The length of email maximum 100 characters")
    private String email;

    @NotNull(message = "{validate.user.phone.notBlank}")
    @Pattern(regexp = "^(\\\\+?84|0)(3[2-9]|5[2689]|7[06-9]|8[1-9]|9[0-9])[0-9]{7}$", message = "Invalid phone number")
    private String phone;
}
