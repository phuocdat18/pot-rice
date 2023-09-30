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
import java.util.Optional;

public interface IUserService extends UserDetailsService {

    List<UserResult> findAll();

    User findById(Long id);

    UserResult getById(Long id);

    User getByUsername(String username);

    List<User> findAllUser();

    List<User> findByUsername(String username);

    List<User> findUserByUsername(String username);


    Boolean existsByUsername(String email);

    @Transactional
    UserResult update(Long id, UserUpdateParam param);

    @Transactional
    UserResult create(UserCreationParam param);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void deleteById(Long id);
}
