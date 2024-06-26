package com.geekbrains.ru.gb_rest_angular.core.service;

import com.geekbrains.ru.gb_rest_angular.core.converter.UserConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Role;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.dto.UserDto;
import com.geekbrains.ru.gb_rest_angular.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder passwordEncoder;

    public Optional<User> findUserByUserName (String name){
        return userRepository.findUserByUserName(name);
    }

    public Optional<User> findUserByUserId (Long id) {return userRepository.findById(id);}

    public Optional<User> findUserByEmail (String email){ return userRepository.findUserByEmail(email);}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findUserByEmail(email);
        User user = optional.get();

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public Page<UserDto> findAllUsers(int page){
        Pageable pageable = PageRequest.of(page-1, 5);
        Page<UserDto> map = userRepository.findAll(pageable).map(u -> userConverter.entityToDto(u));
        return map;
    }

    public void deleteUserById(Long id){userRepository.deleteById(id);}

    public User addNewUser (User user){
        Optional<User> optionalUserByEmail = userRepository.findUserByEmail(user.getEmail());
        if (optionalUserByEmail.isPresent()){
            User user1 = optionalUserByEmail.get();
            user1.setUserName(user.getUserName());
            user1.setSurname(user.getSurname());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setEmail(user.getEmail());
            user1.setPhone(user.getPhone());
            user1.setRoles(user.getRoles());
            return userRepository.save(user1);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }
}
