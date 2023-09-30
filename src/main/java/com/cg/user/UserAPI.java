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

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable String id) throws IOException {
//        if (!validateUtils.isNumberValid(id)) {
//            throw new DataInputException("ID user không hợp lệ");
//        }
//        Long userId = Long.parseLong(id);
//
//        Optional<User> user = userService.findById(userId);
//
//        if (user.isPresent()) {
//            userService.delete(user.get());
//
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        } else {
//            throw new DataInputException("Invalid product information");
//        }
//    }

//    @PatchMapping("/disable/{id}")
//    public ResponseEntity<?> markProductAsDeleted(@PathVariable String id) {
//        if (!validateUtils.isNumberValid(id)) {
//            throw new DataInputException("ID không hợp lệ");
//        }
//        Long userId = Long.parseLong(id);
//
//        Optional<User> userOptional = userService.findById(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setDeleted(true); // Thay đổi thuộc tính 'deleted' thành true
//            userService.save(user);
//            return new ResponseEntity<>("Product has been marked as deleted.", HttpStatus.OK);
//        } else {
//            throw new DataInputException("Invalid product information");
//        }
//    }
//    @PatchMapping("/restore/{id}")
//    public ResponseEntity<?> restoreUser(@PathVariable String id) {
//        if (!validateUtils.isNumberValid(id)) {
//            throw new DataInputException("ID không hợp lệ");
//        }
//        Long userId = Long.parseLong(id);
//
//        Optional<User> userOptional = userService.findById(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            user.setDeleted(false);
//            userService.save(user);
//            return new ResponseEntity<>("Product has been marked as deleted.", HttpStatus.OK);
//        } else {
//            throw new DataInputException("Invalid product information");
//        }
//    }
}
