package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.api.JwtRequest;
import com.geekbrains.ru.gb_rest_angular.api.JwtResponse;
import com.geekbrains.ru.gb_rest_angular.core.converter.RoleConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Role;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.dto.RoleDto;
import com.geekbrains.ru.gb_rest_angular.core.service.UserService;
import com.geekbrains.ru.gb_rest_angular.core.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final RoleConverter roleConverter;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        User user = userService.findUserByEmail(authRequest.getEmail()).get();
        String username = user.getUserName() + " " + user.getSurname();
        Boolean isAdmin;
        Optional<Role> role_admin = user.getRoles().stream().filter(role -> role.getName().equals("ROLE_ADMIN")).findFirst();
        if (role_admin.isPresent()){
            isAdmin = true;
        }
        else isAdmin = false;

        return ResponseEntity.ok(new JwtResponse(token, username, isAdmin));
    }

}
