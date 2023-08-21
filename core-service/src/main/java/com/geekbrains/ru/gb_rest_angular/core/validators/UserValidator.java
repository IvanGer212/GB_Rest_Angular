package com.geekbrains.ru.gb_rest_angular.core.validators;

import com.geekbrains.ru.gb_rest_angular.core.dto.UserDtoRegistr;
import com.geekbrains.ru.gb_rest_angular.core.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public void validate (UserDtoRegistr user){
        List<String> errors = new ArrayList<>();
        String userName = user.getUserName();
        String surname = user.getSurname();
        String email = user.getEmail();
        String phone = user.getPhone();
        String password = user.getPassword();
        String role = user.getRoles();
        if (userName == null){
            errors.add("Необходимо заполнить имя пользователя!");
        } else {
            if (userName.isEmpty()) {
                errors.add("Имя пользователя не может быть пустым!");
            }
        }

        if (surname == null){
            errors.add("Необходимо заполнить фамилию пользователя!");
        } else {
            if (surname.isEmpty()) {
                errors.add("Фамилия пользователя не может быть пустой!");
            }
        }

        if (email == null){
            errors.add("Необходимо заполнить email пользователя!");
        } else {
            if (!email.contains("@")) {
                errors.add("Введен не корректный email пользователя!");
            } else if (email.isEmpty()){
                errors.add("email пользователя не может быть пустой!");
            }
        }

        if (phone == null){
            errors.add("Необходимо заполнить номер телефона пользователя");
        } else {
            if (phone.isEmpty()) {
                errors.add("Номер телефона пользователя не может быть пустой!");
            }
            else if (!(phone.startsWith("+") || phone.startsWith("8")))
                errors.add("Введен не корректный Номер телефона пользователя!");
        }


        if (password == null){
            errors.add("Необходимо придумать пароль пользователя!");
        } else {
            if (password.isEmpty()) {
                errors.add("Пароль пользователя не может быть пустой!");
            }
        }

        if (role == null){
            errors.add("Необходимо назначить роль пользователю!");
        }

        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }

}
