package com.geekbrains.ru.gb_rest_angular.service;

import com.geekbrains.ru.gb_rest_angular.domain.ProductForBin;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;

import java.util.List;

public interface BinCardService {

    void addProductOnBin(ProductDto productDto);

    List<ProductForBin> findAllProductOnBin();

    void deleteProductOnBin(String name);
}
