package com.geekbrains.ru.gb_rest_angular.service.impl;

import com.geekbrains.ru.gb_rest_angular.domain.BinCart;
import com.geekbrains.ru.gb_rest_angular.domain.Order;
import com.geekbrains.ru.gb_rest_angular.domain.OrderItems;
import com.geekbrains.ru.gb_rest_angular.domain.User;
import com.geekbrains.ru.gb_rest_angular.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;

    @Override
    public void createOrder(User user) {
        BinCart binCart = new BinCart();
        Order order = new Order();
//        binCart.getProductsForBin().stream().map(cartItem->{
//            new OrderItems(null, productService.findProductById(cartItem.getId()), order)
//        });
    }
}
