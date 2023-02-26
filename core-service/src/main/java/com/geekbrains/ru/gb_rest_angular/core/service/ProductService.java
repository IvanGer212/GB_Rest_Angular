package com.geekbrains.ru.gb_rest_angular.core.service;



import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<Product> find (Integer minPrice, Integer maxPrice, String title, Integer page);
    List<Product> getAllProduct();

    Optional<Product> findProductById(Long id);

    Product  addNewProduct(Product product);

    void deleteProductById(Long id);

    List<Product> findAllByCostBetween(int min, int max );

    Product update (ProductDto productDto);

    List<Product> findProductsByCategoryId(Long categoryId);
}
