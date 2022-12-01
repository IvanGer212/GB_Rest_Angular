package com.geekbrains.ru.gb_rest_angular.service;

import com.geekbrains.ru.gb_rest_angular.domain.BinCart;
import com.geekbrains.ru.gb_rest_angular.domain.ProductForBin;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;

import java.util.List;

public interface BinCartService {

    void addProductOnBin(ProductDto productDto);

    BinCart findAllProductOnBin();

    void deleteProductOnBin(String name);

    void clearCart();

    void changeScore(String name, String mark);
}
