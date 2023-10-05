package com.cg.user;

import com.cg.role.IRoleService;
import com.cg.role.dto.RoleResult;
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserAPI {
    private final IUserService userService;
    private final IRoleService IRoleService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResult findById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<?> findAll() {
        return userService.findAll();
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResult> getAllRoles() {
        return IRoleService.findAll();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResult update(@PathVariable Long id, @RequestBody UserUpdateParam param) {
        return userService.update(id, param);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserResult findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}
