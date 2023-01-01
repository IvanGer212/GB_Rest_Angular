package com.geekbrains.ru.gb_rest_angular.repository;

import com.geekbrains.ru.gb_rest_angular.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
