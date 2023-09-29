package com.cg.user.dto;

import com.cg.role.dto.RoleResult;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserResult {
    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
<<<<<<< HEAD:src/main/java/com/cg/user/dto/UserResDTO.java
    private RoleResult roleResult;
//    private boolean deleted;
=======
    private RoleDTO role;

>>>>>>> thi-dev:src/main/java/com/cg/user/dto/UserResult.java
}
