package com.geekbrains.ru.gb_rest_angular.service.impl;


import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.repository.ProductRepository;
import com.geekbrains.ru.gb_rest_angular.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Product> firstByTitle = productRepository.findFirstByTitle(product.getTitle());
        if (firstByTitle.isPresent()) {
            Product product1 = firstByTitle.get();
            product1.setTitle(product.getTitle());
            product1.setCost(product.getCost());
            return productRepository.save(product1);
        } else {
            return productRepository.save(product);
        }
    }

    @Override
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAllByCostBetween(int min, int max) {
        return productRepository.findAllByCostBetween(min, max);
    }
}
