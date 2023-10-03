package com.cg.user;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.User;
import com.cg.model.UserPrincipal;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
    public UserResult findByUsername(String username) {
        User entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("username invalid"));
        return userMapper.toDTO(entity);
    }

    @Override
    public void validateByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new DataInputException(new HashMap<>() {
                {
                    put("username", "username invalid");
                }
            });
        }
    }

    @Override
    public void validateByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DataInputException(new HashMap<>() {
                {
                    put("username", "username invalid");
                }
            });
        }
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
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("username invalid"));
        return UserPrincipal.build(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
