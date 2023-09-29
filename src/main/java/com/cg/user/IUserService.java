package com.cg.user;

import com.cg.model.User;
import com.cg.service.IGeneralService;
<<<<<<< HEAD
import com.cg.user.dto.UserDTO;
import com.cg.user.dto.UserUpdateReqDTO;
=======
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
>>>>>>> thi-dev
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
