package com.geekbrains.ru.gb_rest_angular.service.impl;


import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import com.geekbrains.ru.gb_rest_angular.repository.ProductRepository;
import com.geekbrains.ru.gb_rest_angular.repository.specifications.ProductSpecification;
import com.geekbrains.ru.gb_rest_angular.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<Product> find (Integer minPrice, Integer maxPrice, String title, Integer page){
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecification.priceGreaterThanOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecification.priceLessThanOrEqualsThan(maxPrice));
        }
        if (title != null) {
            spec = spec.and(ProductSpecification.titleLike(title));
        }
        return productRepository.findAll(spec, PageRequest.of(page-1, 5));
    }
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductDto> findProductById(Long id) {
        ProductDto productDto;
        Product productById = productRepository.findById(id).get();
        productDto = new ProductDto(productById);
        return Optional.of(productDto);
    }

    @Override
    public ProductDto addNewProduct(ProductDto productDto) {
        Product newProduct = new Product();
        Product saveProduct;
        newProduct.setTitle(productDto.getTitle());
        newProduct.setCost(productDto.getCost());
        Optional<Product> firstByTitle = productRepository.findFirstByTitle(newProduct.getTitle());
        if (firstByTitle.isPresent()) {
            Product product1 = firstByTitle.get();
            product1.setTitle(newProduct.getTitle());
            product1.setCost(newProduct.getCost());
            saveProduct = productRepository.save(product1);
        } else {
            saveProduct = productRepository.save(newProduct);

        }

        return new ProductDto(saveProduct);
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
