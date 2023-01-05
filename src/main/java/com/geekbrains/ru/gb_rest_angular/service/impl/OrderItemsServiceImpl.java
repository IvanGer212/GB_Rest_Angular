package com.geekbrains.ru.gb_rest_angular.service.impl;

import com.geekbrains.ru.gb_rest_angular.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.repository.OrderItemsRepository;
import com.geekbrains.ru.gb_rest_angular.service.OrderItemsService;
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
