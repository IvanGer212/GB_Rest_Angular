package com.geekbrains.ru.gb_rest_angular.dto;

import com.geekbrains.ru.gb_rest_angular.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String name;
    private Collection<User> users;
}
