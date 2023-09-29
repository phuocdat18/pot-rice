package com.cg.user;

import com.cg.model.User;
import com.cg.service.IGeneralService;
import com.cg.user.dto.UserDTO;
import com.cg.user.dto.UserUpdateReqDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User, Long>, UserDetailsService {
    User getByUsername(String username);
    List<UserDTO> findAllUserDTO();

    Optional<User> findByUsername(String username);

    Optional<UserDTO> findUserDTOByUsername(String username);

    Boolean existsByUsername(String email);

    User update(User user, UserUpdateReqDTO userUpdateReqDTO);

}
