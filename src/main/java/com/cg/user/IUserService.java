package com.cg.user;

import com.cg.model.User;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserResult> findAll();

    User findById(Long id);

    UserResult getById(Long id);

    UserResult findByUsername(String username);


    void validateByUsername(String email);

    void validateByEmail(String email);

    UserResult update(Long id, UserUpdateParam param);

    UserResult create(UserCreationParam param);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void deleteById(Long id);
}
