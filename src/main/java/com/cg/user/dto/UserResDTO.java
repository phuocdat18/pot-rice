package com.cg.user.dto;

import com.cg.role.dto.RoleResult;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserResDTO {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private RoleResult roleResult;
//    private boolean deleted;
}
