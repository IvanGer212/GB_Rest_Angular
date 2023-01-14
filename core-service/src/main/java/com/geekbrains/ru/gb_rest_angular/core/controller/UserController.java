package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.core.converter.UserConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.dto.UserDto;
import com.geekbrains.ru.gb_rest_angular.core.dto.UserDtoRegistr;
import com.geekbrains.ru.gb_rest_angular.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/users")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public List<UserDto> getUsers () {
        List<UserDto> userDtoList = userService.findAllUsers().stream().map(u->userConverter.entityToDto(u)).collect(Collectors.toList());
        return userDtoList;
    }


//    @GetMapping("/{id}")
//    @ResponseBody
//    public UserDto getUserById(@PathVariable Long id){
//        User user = userService.findUserByUserId(id).orElseThrow(()-> new ResourceNotFoundException("User not found! id= "+id));
//        return userConverter.entityToDto(user);
//    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        userService.deleteUserById(id);
    }


    @PostMapping
    public UserDto addUser(@RequestBody UserDtoRegistr newUserDto) {
        //productValidator.validate(newProductDto);
        User user = userConverter.dtoRegistrToEntity(newUserDto);
        User user1 = userService.addNewUser(user);
        return userConverter.entityToDto(user);
    }

//    @PutMapping
//    public ProductDto updateProduct(@RequestParam ProductDto productDto){
//        productValidator.validate(productDto);
//        Product product = productService.update(productDto);
//        return productConverter.entityToDto(product);
//    }

}
