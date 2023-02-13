package com.geekbrains.ru.gb_rest_angular.cart.service;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.cart.integrations.ProductServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.cart.model.BinCart;

public interface BinCartService {

    void addProductOnBin(String uuid, Long id);

    BinCart getCurrentCart(String uuid);

    void deleteProductOnBin(String uuid, Long id);

    void clearCart(String uuid);

    void changeScore(String uuid, Long id, String mark);
}
