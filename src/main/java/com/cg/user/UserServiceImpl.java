package com.cg.user;

import com.cg.model.RoleCode;
import com.cg.model.User;
import com.cg.model.UserPrincipal;
import com.cg.role.RoleRepository;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserResult;
import com.cg.user.dto.UserUpdateParam;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.rananu.shared.exceptions.NotFoundException;
import vn.rananu.shared.exceptions.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserResult> findAll() {
        List<User> entities = userRepository.findAll();
        return userMapper.toDTOList(entities);
    }

<<<<<<< HEAD
//    @Override
//    @Transactional(readOnly = true)
//    public List<UserResult> findAll() {
//        List<User> entities = userRepository.findAll();
//        return entities.stream()
//                .map(user -> modelMapper.map(user, UserResult.class))
//                .collect(Collectors.toList());
//    }


=======
>>>>>>> dat-dev
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not found"));
    }

//    @Override
//    @Transactional(readOnly = true)
//    public UserResult getById(Long id) {
//        User entity = findById(id);
//        return userMapper.toDTO(entity);
//    }

    @Override
    @Transactional(readOnly = true)
    public UserResult getById(Long id) {
        User user = findById(id);
        return modelMapper.map(user, UserResult.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResult findByUsername(String username) {
        User entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("username invalid"));
        return modelMapper.map(entity, UserResult.class);
    }

    @Override
    @Transactional(readOnly = true)
    public void validateByUsername(String username) {
        if (userRepository.existsByUsername(username))
            throw new ValidationException("username", "validate.user.username.existed");

    }

    @Override
    @Transactional(readOnly = true)
    public void validateByEmail(String email) {
        if (userRepository.existsByEmail(email))
            throw new ValidationException("email", "validate.user.email.existed");

    }

    @Override
    @Transactional
    public UserResult update(Long id, UserUpdateParam param) {
        User entity = findById(id);
        userMapper.transferFields(entity, param);
        return modelMapper.map(entity, UserResult.class);
    }

    @Override
    @Transactional
    public UserResult signup(UserCreationParam creationParam) {
        validateByUsername(creationParam.getUsername());
        validateByEmail(creationParam.getEmail());

//        User entity = userMapper.toEntity(creationParam);
        User entity = modelMapper.map(creationParam, User.class);
        entity.setRoleId(RoleCode.CUSTOMER);
        String passwordEncode = passwordEncoder.encode(creationParam.getPassword());
        entity.setPassword(passwordEncode);
        entity = userRepository.save(entity);
        return modelMapper.map(entity, UserResult.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("username invalid"));
        return UserPrincipal.build(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
