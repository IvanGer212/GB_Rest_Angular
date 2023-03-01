package com.geekbrains.ru.gb_rest_angular.core.service.impl;


import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.repository.ProductRepository;
import com.geekbrains.ru.gb_rest_angular.core.repository.specifications.ProductSpecification;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> find (Integer minPrice, Integer maxPrice, String title, Integer page, Long categoryId){
        Page<Product> page1;
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

        if (categoryId != null){
            List<Product> all = productRepository.findAll(spec);

            List<Product> collect = all.stream().filter(product -> product.getCategory().getId().equals(categoryId)).collect(Collectors.toList());

            page1 = new PageImpl<Product>(collect, PageRequest.of(page-1,5), all.size());

        }
            else {page1 = productRepository.findAll(spec, PageRequest.of(page-1, 5));}
        return page1; //productRepository.findAll(spec, PageRequest.of(page-1, 5));
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

    @Override
    @Transactional
    public Product update(ProductDto productDto){
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукт, не найден в базе, id = "+productDto.getId()));
        product.setCost(productDto.getCost());
        product.setTitle(productDto.getTitle());
        return product;
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return productRepository.findAllByCategory_Id(categoryId);
    }

}
