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
        return modelMapper.map(creationParam,Role.class);
    }

    public Role toEntity(RoleUpdateParam dto) {
        return null;
    }

    public void transferFields(Role entity, RoleUpdateParam dto) {
        modelMapper.map(entity,dto);
    }

    public RoleResult toDTO(Role entity) {
        return modelMapper.map(entity, RoleResult.class);
    }

    public List<RoleResult> toDTOList(List<Role> entities) {
        return entities.stream().map(entity->modelMapper.map(entity, RoleResult.class)).collect(Collectors.toList());
    }


}
