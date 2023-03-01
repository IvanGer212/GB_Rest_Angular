package com.geekbrains.ru.gb_rest_angular.core.service;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderItemsService {

    void addItemOnOrder(OrderItem orderItem);

    List<OrderItem> findAllItemsByOrderId(Long orderId);

}
