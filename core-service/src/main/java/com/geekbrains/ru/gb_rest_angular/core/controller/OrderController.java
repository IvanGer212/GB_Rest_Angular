package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.core.converter.OrderConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderDtoResponse;
import com.geekbrains.ru.gb_rest_angular.core.dto.OrderItemDto;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/order")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (Principal principal){
        User user = userService.findUserByEmail(principal.getName()).get();
        orderService.createOrder(user);
    }

    @GetMapping
    public List<OrderDtoResponse> getOrders(Principal principal){
        String email = userService.findUserByEmail(principal.getName()).get().getEmail();
        return orderService.getOrders(email).stream().map(orderConverter::entityToDtoResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<OrderItemDto> getOrderItemsById(@PathVariable Long id){
        return orderService.findItemsByOrderId(id);
    }

}
