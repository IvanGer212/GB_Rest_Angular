package com.geekbrains.ru.gb_rest_angular.core.service;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemsService {

    void addItemOnOrder(OrderItem orderItem);
}
