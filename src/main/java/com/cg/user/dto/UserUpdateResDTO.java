package com.cg.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserUpdateResDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
}
