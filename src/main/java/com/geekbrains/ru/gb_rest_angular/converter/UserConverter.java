package com.geekbrains.ru.gb_rest_angular.converter;

import com.geekbrains.ru.gb_rest_angular.domain.Order;
import com.geekbrains.ru.gb_rest_angular.domain.Role;
import com.geekbrains.ru.gb_rest_angular.domain.User;
import com.geekbrains.ru.gb_rest_angular.dto.OrderDto;
import com.geekbrains.ru.gb_rest_angular.dto.UserDto;
import com.geekbrains.ru.gb_rest_angular.dto.UserDtoRegistr;
import com.geekbrains.ru.gb_rest_angular.service.RoleService;
import com.geekbrains.ru.gb_rest_angular.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final UserService userService;
    private final RoleService roleService;

    public User dtoToEntity (UserDto userDto) {
    User user = new User();
    user.setId(userDto.getId());
    user.setUserName(userDto.getUserName());
    user.setSurname(userDto.getSurname());
    user.setPhone(userDto.getPhone());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
        List<String> roles = userDto.getRoles();
        List<String> newRoles = new ArrayList<>();
        for (String role: roles) {
            if (role.equals("user")){
                newRoles.add("ROLE_USER");
            } else if (role.equals("admin")){
                newRoles.add("ROLE_ADMIN");
            } else newRoles.add("ROLE_USER");
        }
        user.setRoles(newRoles.stream().map(r-> new Role(r)).collect(Collectors.toList()));

    return user;
    }

    public UserDto entityToDto (User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setSurname(user.getSurname());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
    return userDto;
    }

    public User dtoRegistrToEntity (UserDtoRegistr userDtoRegistr) {
        User user = new User();
        user.setId(userDtoRegistr.getId());
        user.setUserName(userDtoRegistr.getUserName());
        user.setSurname(userDtoRegistr.getSurname());
        user.setPhone(userDtoRegistr.getPhone());
        user.setEmail(userDtoRegistr.getEmail());
        user.setPassword(userDtoRegistr.getPassword());
        String role = userDtoRegistr.getRoles();
            if (role.equals("user")){
                role = "ROLE_USER";
            } else if (role.equals("admin")){
                role = "ROLE_ADMIN";
            } else role = ("ROLE_USER");

        Role roleByName = roleService.findRoleByName(role);
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleByName);
        user.setRoles(roleList);

        return user;
    }


//        public Order dtoToEntity (OrderDto orderDto){
//            Order order = new Order();
//            order.setId(orderDto.getId());
//            order.setAddress(orderDto.getAddress());
//            order.setPhone(order.getPhone());
//            User user = userService.findUserByUserId(orderDto.getUserId()).get();
//            return order;
//        }

}

