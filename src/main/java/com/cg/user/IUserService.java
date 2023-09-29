package com.cg.user;

import com.cg.model.User;
import com.cg.model.dto.user.UserDTO;
import com.cg.model.dto.user.UserUpdateReqDTO;
import com.cg.service.IGeneralService;
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User, Long>, UserDetailsService {
    User getByUsername(String username);
    List<UserResult> findAllUserDTO();

    Optional<User> findByUsername(String username);

    Optional<UserResult> findUserDTOByUsername(String username);

    Boolean existsByUsername(String email);

    User update(User user, UserUpdateParam userUpdateReqDTO);

}
