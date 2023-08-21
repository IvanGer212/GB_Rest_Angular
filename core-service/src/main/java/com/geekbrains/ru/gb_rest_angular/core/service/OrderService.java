package com.geekbrains.ru.gb_rest_angular.core.service;

import com.geekbrains.ru.gb_rest_angular.core.domain.Order;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Order createOrder(User user);

    List<Order> getOrders(String username);

    List<OrderItemDto> findItemsByOrderId(Long orderId);
}
