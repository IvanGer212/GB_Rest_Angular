package com.geekbrains.ru.gb_rest_angular.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String userName;
    private String surname;
    private String password;
    private String email;
    private String phone;
    private List<String> roles;


    public UserDto(Long id, String userName, String surname, String email, String phone, List<String> roles) {
        this.id = id;
        this.userName = userName;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }

}
