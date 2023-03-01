package com.geekbrains.ru.gb_rest_angular.core.repository;

import com.geekbrains.ru.gb_rest_angular.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUsername(String username);
}
