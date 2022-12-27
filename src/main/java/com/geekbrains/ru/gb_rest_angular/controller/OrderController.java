package com.geekbrains.ru.gb_rest_angular.controller;

import com.geekbrains.ru.gb_rest_angular.domain.User;
import com.geekbrains.ru.gb_rest_angular.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/orders")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (Principal principal /*, @RequestBody OrderData orderData */){
        User user = userService.findByUsername(principal.getName()).orElseThrow(()-> new RuntimeException());
        orderService.createOrder(user);
    }
}
