package com.cg.role;

import com.cg.model.Role;
import com.cg.role.dto.BaseRole;
import com.cg.role.dto.RoleCreationParam;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.rananu.shared.mappers.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleMapper extends BaseMapper<RoleResult,Role, BaseRole> {

}
