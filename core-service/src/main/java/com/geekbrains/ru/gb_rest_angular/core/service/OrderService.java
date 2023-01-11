package com.geekbrains.ru.gb_rest_angular.core.service;

import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    void createOrder(User user);
}
