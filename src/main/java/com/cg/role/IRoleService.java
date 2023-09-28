package com.cg.role;

import com.cg.model.ERole;
import com.cg.model.Role;
import com.cg.role.dto.RoleCreationParam;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoleService {
    Role findById(Long id);

    RoleResult getById(Long id);

    RoleResult create(RoleCreationParam param);


    Role findByName(ERole name);

    List<RoleResult> findAll();

    RoleResult update(Long id, RoleUpdateParam param);

    void deleteById(Long id);
}
