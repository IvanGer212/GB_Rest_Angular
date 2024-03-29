package com.geekbrains.ru.gb_rest_angular.core.service.impl;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
import com.geekbrains.ru.gb_rest_angular.core.converter.OrderItemConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Order;
import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderItemDto;
import com.geekbrains.ru.gb_rest_angular.core.integrations.CartServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderItemsService;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import com.geekbrains.ru.gb_rest_angular.core.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final OrderItemsService orderItemsService;
    private final OrderItemConverter orderItemConverter;

    @Override
    @Transactional
    public Order createOrder(User user) {
        Order order = new Order();
        BinCartDto cart = cartServiceIntegration.getCurrentCart(user.getEmail());
        order.setUsername(user.getEmail());
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

     @Override
    public Page<Order> getOrders(int pageNum, String username){
        Pageable pageable = PageRequest.of(pageNum-1, 5);
         return orderRepository.findAllByUsername(pageable, username);
//        Page<Order> page;
//         List<Order> allByUsername = orderRepository.findAllByUsername(username);
//         page = new PageImpl<Order>(allByUsername,pageable, allByUsername.size());
        //return page;
     }

     @Override
     public List<OrderItemDto> findItemsByOrderId (Long orderId){
         return orderItemsService.findAllItemsByOrderId(orderId).stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
     }

     @Override
    public Page<Order> getAllOrders(int pageNum){
         Pageable pageable = PageRequest.of(pageNum-1,5);
         return orderRepository.findAll(pageable);
     }

}

