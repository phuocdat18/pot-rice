package com.cg.api;

import com.cg.model.JwtResponse;
import com.cg.role.IRoleService;
import com.cg.service.jwt.JwtService;
import com.cg.user.IUserService;
import com.cg.user.UserMapper;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserLoginDTO;
import com.cg.user.dto.UserResult;
import com.cg.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.rananu.shared.exceptions.OperationException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthAPI {
    private final UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreationParam creationParam) {

        userService.validateByUsername(creationParam.getUsername());

        try {
            String passwordEncode = passwordEncoder.encode(creationParam.getPassword());
            creationParam.setPassword(passwordEncode);
            UserResult user = userService.create(creationParam);

            return new ResponseEntity<>(userMapper.toEntity(user), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new OperationException("Account information is not valid, please check the information again");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO user, BindingResult bindingResult) {

        UserResult currentUser = userService.findByUsername(user.getUsername());
        if (currentUser.isDeleted())
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        JwtResponse jwtResponse = new JwtResponse(
                jwt,
                currentUser.getId(),
                userDetails.getUsername(),
                currentUser.getUsername(),
                userDetails.getAuthorities()
        );

        ResponseCookie springCookie = ResponseCookie.from("JWT", jwt)
                .httpOnly(false)
                .secure(false)
                .path("/")
                .maxAge(1000L * 60 * 60 * 24 * 30)
                .domain("localhost")
                .build();

        System.out.println(jwtResponse);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, springCookie.toString())
                .body(jwtResponse);


    }
}
