package com.cg.user;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.User;
<<<<<<< HEAD
import com.cg.model.UserPrinciple;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserResult;
=======
import com.cg.model.UserPrincipal;
>>>>>>> thi-dev
import com.cg.user.dto.UserUpdateParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public List<UserResult> findAllUserDTO() {
        return userRepository.findAllUserDTO();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserResult> findUserDTOByUsername(String username) {
        return userRepository.findUserDTOByUsername(username);
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
    public UserResult create(UserCreationParam param) {     //DTO khi request
        User entity = userMapper.toEntity(param);
        entity = userRepository.save(entity);
        return userMapper.toDTO(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrincipal.build(userOptional.get());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
