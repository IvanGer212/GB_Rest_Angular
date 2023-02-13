package com.geekbrains.ru.gb_rest_angular.core.service.impl;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
import com.geekbrains.ru.gb_rest_angular.core.domain.Order;
import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.integrations.CartServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import com.geekbrains.ru.gb_rest_angular.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;

    @Override
    @Transactional
    public Order createOrder(User user) {
        Order order = new Order();
        BinCartDto cart = cartServiceIntegration.getCurrentCart(user.getEmail());
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
        cartServiceIntegration.clear(user.getEmail());
        return order;
     }

}

