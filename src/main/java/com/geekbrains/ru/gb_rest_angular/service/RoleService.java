package com.geekbrains.ru.gb_rest_angular.service;

import com.geekbrains.ru.gb_rest_angular.domain.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role findRoleByName (String name);
}
