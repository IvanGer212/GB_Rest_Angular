package com.geekbrains.ru.gb_rest_angular.repository;

import com.geekbrains.ru.gb_rest_angular.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
