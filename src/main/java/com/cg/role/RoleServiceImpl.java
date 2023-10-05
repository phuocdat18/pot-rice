package com.cg.role;

import com.cg.model.Role;
import com.cg.model.RoleCode;
import com.cg.role.dto.RoleCreationParam;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.rananu.shared.exceptions.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<RoleResult> findAll() {
        List<Role> entities = roleRepository.findAll();
        return roleMapper.toDTOList(entities);
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("role not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResult getById(String id) {
        Role entity = findById(id);
        return roleMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public RoleResult create(RoleCreationParam param) {
        Role entity = roleMapper.toEntity(param);
        entity = roleRepository.save(entity);
        return roleMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public RoleResult update(String id, RoleUpdateParam param) {
        Role entity = findById(id);
        roleMapper.transferFields(param,entity);
        return roleMapper.toDTO(entity);
    }


}
