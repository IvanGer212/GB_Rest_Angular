package com.geekbrains.ru.gb_rest_angular.service.impl;

import com.geekbrains.ru.gb_rest_angular.domain.*;
import com.geekbrains.ru.gb_rest_angular.repository.OrderRepository;
import com.geekbrains.ru.gb_rest_angular.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BinCartService cartService;
    private final ProductService productService;

    @Override
    @Transactional
    public void createOrder(User user) {
        Order order = new Order();
        BinCart cart = cartService.findAllProductOnBin();
        order.setUser(user);
        order.setCost(cart.getTotalPrice());
        order.setItems(cart.getProductsForBin().stream().map(
                    cartItem-> new OrderItem(
                            productService.findProductById(cartItem.getId()).get(),
                            order,
                            cartItem.getQuantity(),
                            cartItem.getPricePerProduct(),
                            cartItem.getPrice()
                    )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
     }

}

