package com.geekbrains.ru.gb_rest_angular.service;

import com.geekbrains.ru.gb_rest_angular.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    void createOrder(User user);
}
