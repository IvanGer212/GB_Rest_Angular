package com.geekbrains.ru.gb_rest_angular.core.converter;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.domain.Role;
import com.geekbrains.ru.gb_rest_angular.core.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleConverter {

//        public Role dtoToEntity (RoleDto roleDto){
//            return new Role(roleDto.getId(), roleDto.getName());
//        }

        public RoleDto entityToDto (Role role){
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getId());
            roleDto.setName(role.getName());
            return roleDto;
        }
}
