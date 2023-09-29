package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.JwtResponse;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.role.IRoleService;
import com.cg.service.jwt.JwtService;
import com.cg.user.IUserService;
import com.cg.user.dto.UserCreationParam;
import com.cg.user.dto.UserLoginDTO;
import com.cg.utils.AppUtils;
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

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

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
    public ResponseEntity<?> register(@Valid @RequestBody UserCreationParam userReqDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Boolean existsByUsername = userService.existsByUsername(userReqDTO.getUsername());

        if (existsByUsername) {
            throw new EmailExistsException("Account already exists");
        }

        Optional<Role> optRole = Optional.ofNullable(roleService.findById(userReqDTO.getRole().getId()));

        try {
            String passwordEncode = passwordEncoder.encode(userReqDTO.getPassword());
            userReqDTO.setPassword(passwordEncode);
            User user = userService.save(userReqDTO.toUser(null));

            return new ResponseEntity<>(user.toUserLoginDTO(), HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DataInputException("Account information is not valid, please check the information again");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        try {
            User currentUser = userService.getByUsername(user.getUsername());
            if(currentUser.isDeleted()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
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
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
