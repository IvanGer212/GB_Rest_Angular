package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (Principal principal){
        User user = userService.findUserByEmail(principal.getName()).get();
        orderService.createOrder(user);
    }

}
