package com.cg.user.dto;

import com.cg.model.Role;
import com.cg.model.User;
import com.cg.role.dto.RoleResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String phone;
    private RoleResult roleResult;
    private boolean deleted;

    public UserDTO(Long id, String fullName, String username, String email, String phone, RoleResult roleResult ) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.roleResult = roleResult;
    }
    public UserDTO(Long id, String fullName, String username, String email, String phone, RoleResult roleResult, boolean deleted ) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.roleResult = roleResult;
        this.deleted = deleted;
    }



//    public UserDTO(Long id, String username) {
//        this.id = id;
//        this.username = username;
//    }
//
//    public User toUser() {
//        return new User()
//                .setId(id)
//                .setFullName(fullName)
//                .setUsername(username)
//                .setPassword(password)
//                .setEmail(email)
//                .setPhone(phone)
//                .setRole(role.toRole())
//                ;
//    }
}
