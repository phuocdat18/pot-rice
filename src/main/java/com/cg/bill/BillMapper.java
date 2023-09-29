package com.cg.bill;

import com.cg.bill.dto.BillCreationParam;
import com.cg.model.Bill;
import com.cg.model.Role;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BillMapper {
    public Bill toEntity(BillCreationParam creationParam) {
        return new Bill()
                .setUser(creationParam.getUserDTO())
                .
                ;
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

    public RoleResult toDTO(Role entity) {
        return new RoleResult()
                .setId(entity.getId())
                .setCode(entity.getCode())
                .setName(entity.getName());
    }

    public List<RoleResult> toDTOList(List<Role> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
