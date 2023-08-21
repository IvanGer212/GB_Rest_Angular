package com.geekbrains.ru.gb_rest_angular.core.converter;

import com.geekbrains.ru.gb_rest_angular.core.domain.Order;
import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderDto;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto (OrderItem orderItem){
        return new OrderItemDto(orderItem.getProduct().getTitle(), orderItem.getOrder().getId(),orderItem.getQuantity(),orderItem.getPricePerProduct(),orderItem.getCostOrder());
    }
}
