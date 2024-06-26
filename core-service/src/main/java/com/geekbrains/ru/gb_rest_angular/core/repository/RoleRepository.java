package com.geekbrains.ru.gb_rest_angular.core.repository;

import com.geekbrains.ru.gb_rest_angular.core.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

    //Role findByName(String name);
}
