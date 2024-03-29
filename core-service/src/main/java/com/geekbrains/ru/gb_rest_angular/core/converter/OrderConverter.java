package com.geekbrains.ru.gb_rest_angular.core.converter;

import com.geekbrains.ru.gb_rest_angular.core.domain.Order;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderDto;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderDtoResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class OrderConverter {

        public Order dtoToEntity (OrderDto orderDto){
            Order order = new Order();
            order.setId(orderDto.getId());
            order.setAddress(orderDto.getAddress());
            order.setPhone(order.getPhone());
            order.setUsername(orderDto.getUsername());
            return order;
        }

        public OrderDto entityToDto (Order order){
            return new OrderDto(order.getId(), order.getUsername(), order.getItems(), order.getCost(), order.getAddress(), order.getPhone());
        }

    public OrderDtoResponse entityToDtoResponse (Order order){
        return new OrderDtoResponse(order.getId(), order.getUsername(), order.getCost(), order.getAddress(), order.getPhone(), order.getCreatedAt().toLocalDate());
    }
}
