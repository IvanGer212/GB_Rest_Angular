package com.geekbrains.ru.gb_rest_angular.core.service.impl;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.core.repository.OrderItemsRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemsServiceImpl implements OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;


    @Override
    public void addItemOnOrder(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findAllItemsByOrderId(Long orderId){
        List<OrderItem> allByOrder_id = orderItemsRepository.findAllByOrder_Id(orderId);
        return allByOrder_id;
    }


}
