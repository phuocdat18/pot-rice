package com.cg.model;


import com.cg.user.dto.UserDTO;
import com.cg.user.dto.UserLoginDTO;
import com.cg.user.dto.UserUpdateResDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;

    @Column(name = "role_id", insertable = false, updatable = false)
    private int roleId;

    public User(Long id, String fullName, String username, String password, String email,boolean deleted, String phone, Role role, Integer roleId) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.roleId = roleId;
    }

    public User() {

    }

    public UserLoginDTO toUserLoginDTO() {
         return new UserLoginDTO()
                .setUsername(username)
                .setPassword(password);
    }

    public UserDTO toUserDTO() {
        return new  UserDTO()
                .setId(id)
                .setFullName(fullName)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
//                .setRole(role.toRoleDTO())
                ;
    }

    public UserUpdateResDTO toUserUpdateResDTO() {
        return new UserUpdateResDTO()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                ;
    }
}
