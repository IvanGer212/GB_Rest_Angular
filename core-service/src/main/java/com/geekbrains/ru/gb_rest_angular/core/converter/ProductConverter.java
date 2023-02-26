package com.geekbrains.ru.gb_rest_angular.core.converter;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;


    public Product dtoToEntity (ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost(), categoryService.findCategoryById(productDto.getCategory()).get());//categoryService.findCategoryByName(productDto.getCategory()).get());
    }

    public ProductDto entityToDto (Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCost(product.getCost());
        productDto.setTitle(product.getTitle());
        productDto.setCategory(product.getCategory().getId());
        return productDto;
    }
}
