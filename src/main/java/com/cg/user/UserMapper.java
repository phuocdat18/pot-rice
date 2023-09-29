package com.cg.user;

import com.cg.model.Role;
import com.cg.model.User;
import com.cg.role.dto.RoleCreationParam;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserCreationParam creationParam) {
        return new User()
                .setFullName(creationParam.getFullName())
                .setUsername(creationParam.getUsername())
                .setPassword(creationParam.getPassword())
                .setEmail(creationParam.getEmail())
                .setPhone(creationParam.getPhone());
    }

    public Role toEntity(RoleUpdateParam dto) {
//        return new Role()
//                .setName(dto.getName());
        return null;
    }

    public void transferFields(Role entity, RoleUpdateParam dto) {
        entity.setName(dto.getName());
//        return new Role()
//                .setName(dto.getName());
    }

    public UserResult toDTO(User entity) {
        return new UserResult()
                .setId(entity.getId())
                .setFullName(entity.getFullName())
                .setUsername(entity.getUsername())
                .setPassword(entity.getPassword())
                .setEmail(entity.getEmail())
                .setPhone(entity.getPhone());
    }

    public List<RoleResult> toDTOList(List<Role> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
