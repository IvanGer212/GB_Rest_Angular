package com.geekbrains.ru.gb_rest_angular.service.impl;

import com.geekbrains.ru.gb_rest_angular.domain.Role;
import com.geekbrains.ru.gb_rest_angular.repository.RoleRepository;
import com.geekbrains.ru.gb_rest_angular.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
