package com.geekbrains.ru.gb_rest_angular.service.impl;

import com.geekbrains.ru.gb_rest_angular.domain.BinCart;
import com.geekbrains.ru.gb_rest_angular.domain.Order;
import com.geekbrains.ru.gb_rest_angular.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.domain.User;
import com.geekbrains.ru.gb_rest_angular.repository.OrderRepository;
import com.geekbrains.ru.gb_rest_angular.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final BinCartServiceImpl cartService;

    @Override
    public void createOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        BinCart cart = cartService.findAllProductOnBin();
        order.setCost(cart.getTotalPrice());
        Order save = orderRepository.save(order);


    }
}
