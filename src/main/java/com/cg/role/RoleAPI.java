package com.cg.role;

import com.cg.role.dto.RoleCreationParam;
import com.cg.role.dto.RoleResult;
import com.cg.role.dto.RoleUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleAPI {
    private final IRoleService roleService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResult findById(@PathVariable Long id) {
        return roleService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAll() {
        return roleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResult create(@RequestBody RoleCreationParam param) {
        return roleService.create(param);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResult update(@PathVariable Long id, @RequestBody RoleUpdateParam param) {
        return roleService.update(id, param);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
