package com.cg.user;

import com.cg.model.User;
import com.cg.role.RoleMapper;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public User toEntity(UserCreationParam creationParam) {
        return new User()
                .setFullName(creationParam.getFullName())
                .setUsername(creationParam.getUsername())
                .setPassword(creationParam.getPassword())
                .setEmail(creationParam.getEmail())
                .setPhone(creationParam.getPhone());
//        return modelMapper.map(creationParam,User.class);
    }

    public void transferFields(User entity, UserUpdateParam dto) {
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }

    public UserResult toDTO(User entity) {
        return new UserResult()
                .setId(entity.getId())
                .setFullName(entity.getFullName())
                .setUsername(entity.getUsername())
                .setPassword(entity.getPassword())
                .setEmail(entity.getEmail())
                .setPhone(entity.getPhone());
//        return modelMapper.map(entity,UserResult.class);
    }

    public List<UserResult> toDTOList(List<User> entities) {
//        return entities.stream().map(this::toDTO).collect(Collectors.toList());
        return entities.stream().map(entity->modelMapper.map(entity, UserResult.class)).collect(Collectors.toList());

    }
}
