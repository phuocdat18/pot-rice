package com.cg.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "users")
@TypeDef(
        name = "address",
        typeClass = JsonType.class)
@TypeDef(
        name = "extensions",
        typeClass = JsonType.class)
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

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;

    @Column(name = "role_id", length = 20, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private RoleCode roleId;

    @Type(type = "address")
    @Column(name = "address",columnDefinition = "JSON")
    private Address address;
    @Type(type = "extensions")
    @Column(name = "extensions",columnDefinition = "JSON")
    private List<String> extensions=new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }

    public void setRoleId(RoleCode roleId) {
        this.role = new Role(this.roleId = roleId);
    }
}
