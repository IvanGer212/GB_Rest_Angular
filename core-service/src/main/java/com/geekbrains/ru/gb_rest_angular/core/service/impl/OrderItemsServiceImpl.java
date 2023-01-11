package com.geekbrains.ru.gb_rest_angular.core.service.impl;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.core.repository.OrderItemsRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    @Override
    public void addItemOnOrder(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }
}
