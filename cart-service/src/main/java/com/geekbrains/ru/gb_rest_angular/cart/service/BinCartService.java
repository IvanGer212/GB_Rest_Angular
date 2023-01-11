package com.geekbrains.ru.gb_rest_angular.cart.service;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.cart.integrations.ProductServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.cart.model.BinCart;

public interface BinCartService {

    void addProductOnBin(Long id);

    BinCart findAllProductOnBin();

    void deleteProductOnBin(Long id);

    void clearCart();

    void changeScore(Long id, String mark);
}
