package com.geekbrains.ru.gb_rest_angular.service;



import com.geekbrains.ru.gb_rest_angular.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();

    Optional<Product> findProductById(Long id);

    Product  addNewProduct(Product product);

    void deleteProductById(Long id);

    List<Product> findAllByCostBetween(int min, int max );
}
