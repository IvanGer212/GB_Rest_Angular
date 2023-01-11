package com.geekbrains.ru.gb_rest_angular.core.repository;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
