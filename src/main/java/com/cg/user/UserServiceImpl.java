package com.cg.user;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.User;
import com.cg.model.UserPrincipal;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResult> findAll() {
        List<User> entities = userRepository.findAll();
        return userMapper.toDTOList(entities);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResult getById(Long id) {
        User entity = findById(id);
        return userMapper.toDTO(entity);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public UserResult update(Long id, UserUpdateParam param) {
        User entity = findById(id);
        userMapper.transferFields(entity, param);
        return userMapper.toDTO(entity);
    }

    @Override
    @Transactional
    public UserResult create(UserCreationParam param) {
        User entity = userMapper.toEntity(param);
        entity = userRepository.save(entity);
        return userMapper.toDTO(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<User> userOptional = userRepository.findByUsername(username);
        return UserPrincipal.build((User) userOptional);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
