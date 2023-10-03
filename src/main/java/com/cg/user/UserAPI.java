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

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResult update(@PathVariable Long id, @RequestBody UserUpdateParam param) {
        return userService.update(id, param);
    }

//    @PatchMapping("/update/{id}")
//
//    public ResponseEntity<?> updateUser(@PathVariable String id, @ModelAttribute UserUpdateParam userUpdateParam, BindingResult bindingResult) {
//
//        new UserUpdateParam();
//
//        if (bindingResult.hasErrors())
//            return appUtils.mapErrorToResponse(bindingResult);
//
//        if (!validateUtils.isNumberValid(id)) {
//            throw new DataInputException("Mã sản phẩm không hợp lệ");
//        }
//
//        Long userId = Long.parseLong(id);
//
//        Optional<User> userOptional = Optional.ofNullable(userService.findById(userId));
//
//        if (userOptional.isPresent()) {
//            User user = userService.update(userOptional.get(), userUpdateParam);
//            UserUpdateParam userUpdateParam1 = user.toUserUpdateResDTO();
//
//            return new ResponseEntity<>(userUpdateParam1, HttpStatus.OK);
//        } else {
//            throw new DataInputException("Thông tin người dùng không hợp lệ");
//        }
//    }



//    @GetMapping("/{id}")
//    public ResponseEntity<?> findUserById(@PathVariable String id) {
//        if (!validateUtils.isNumberValid(id)) {
//            throw new DataInputException("ID không hợp lệ");
//        }
//        Long userId = Long.parseLong(id);
//
//        try {
//            Optional<User> user = userService.findById(userId);
//
//            if (user.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(user.get().toUserDTO(), HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }



}
