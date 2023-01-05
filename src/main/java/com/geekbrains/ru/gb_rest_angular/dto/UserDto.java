package com.geekbrains.ru.gb_rest_angular.dto;

import com.geekbrains.ru.gb_rest_angular.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private List<Role> roles;
}
