package com.cg.role;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.ERole;
import com.cg.model.Role;
import com.cg.role.dto.RoleCreationParam;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//        List<RoleResult> dtoList = new ArrayList<>();
//        for (Role entity : entities) {
//            RoleResult dto = roleMapper.toDTO(entity);
//            dtoList.add(dto);
//        }
//        return dtoList;
//        return entities.stream().map(roleMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("role not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResult getById(Long id) {
        Role entity = findById(id);
        return roleMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public RoleResult create(RoleCreationParam param) {     //DTO khi request
        Role entity = roleMapper.toEntity(param);
        entity = roleRepository.save(entity);
        return roleMapper.toDTO(entity);
//        return MapUtils.toDTO(entity, RoleResult.class);
    }

    @Override
    @Transactional
    public RoleResult update(Long id, RoleUpdateParam param) {
        Role entity = findById(id);
        roleMapper.transferFields(entity, param);
        return roleMapper.toDTO(entity);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findByName(name);
    }


}
