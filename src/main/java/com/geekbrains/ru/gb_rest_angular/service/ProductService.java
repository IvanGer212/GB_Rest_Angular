package com.geekbrains.ru.gb_rest_angular.service;



import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> find (Integer minPrice, Integer maxPrice, String title, Integer page);
    List<Product> getAllProduct();

    Optional<Product> findProductById(Long id);

    Product  addNewProduct(ProductDto productDto);

    void deleteProductById(Long id);

    List<Product> findAllByCostBetween(int min, int max );
}
