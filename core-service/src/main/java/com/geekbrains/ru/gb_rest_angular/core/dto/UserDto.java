package com.geekbrains.ru.gb_rest_angular.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(userName, userDto.userName) && Objects.equals(surname, userDto.surname) && Objects.equals(password, userDto.password) && Objects.equals(email, userDto.email) && Objects.equals(phone, userDto.phone) && Objects.equals(roles, userDto.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, surname, password, email, phone, roles);
    }
}
