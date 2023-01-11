package com.geekbrains.ru.gb_rest_angular.core.repository;

import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUserName(String name);

    Optional<User> findUserByEmail(String email);
}
