package com.geekbrains.ru.gb_rest_angular.converter;

import com.geekbrains.ru.gb_rest_angular.domain.Order;
import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.domain.User;
import com.geekbrains.ru.gb_rest_angular.dto.OrderDto;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import com.geekbrains.ru.gb_rest_angular.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConverter {
        private final UserService userService;

        public Order dtoToEntity (OrderDto orderDto){
            Order order = new Order();
            order.setId(orderDto.getId());
            order.setAddress(orderDto.getAddress());
            order.setPhone(order.getPhone());
            User user = userService.findUserByUserId(orderDto.getUserId()).get();
            return order;
        }

        public OrderDto entityToDto (Order order){
            return new OrderDto(order.getId(), order.getUser().getId(), order.getItems(), order.getCost(), order.getAddress(), order.getPhone());
        }
}
