package com.geekbrains.ru.gb_rest_angular.converter;

import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product dtoToEntity (ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }

    public ProductDto entityToDto (Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
}
