package com.geekbrains.ru.gb_rest_angular.core.service.impl;

import com.geekbrains.ru.gb_rest_angular.core.domain.Role;
import com.geekbrains.ru.gb_rest_angular.core.repository.RoleRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.RoleService;
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
