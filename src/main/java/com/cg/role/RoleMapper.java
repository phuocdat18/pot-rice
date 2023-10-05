package com.cg.role;

import com.cg.model.Role;
import com.cg.role.dto.RoleCreationParam;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper modelMapper;
    public Role toEntity(RoleCreationParam creationParam) {
//        return new Role()
//                .setId(creationParam.getId())
//                .setName(creationParam.getName());
        return modelMapper.map(creationParam,Role.class);
    }

    public Role toEntity(RoleUpdateParam dto) {
        return null;
    }

    public void transferFields(Role entity, RoleUpdateParam dto) {
        entity.setName(dto.getName());
    }

    public RoleResult toDTO(Role entity) {
//        return new RoleResult()
//                .setId(entity.getId())
//                .setName(entity.getName());
        return modelMapper.map(entity, RoleResult.class);
    }

    public List<RoleResult> toDTOList(List<Role> entities) {
//        return entities.stream().map(this::toDTO).collect(Collectors.toList());
        return entities.stream().map(entity->modelMapper.map(entity, RoleResult.class)).collect(Collectors.toList());
    }


}
