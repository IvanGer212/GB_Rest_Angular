package com.geekbrains.ru.gb_rest_angular.core.repository;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder_Id(Long orderId);
}
