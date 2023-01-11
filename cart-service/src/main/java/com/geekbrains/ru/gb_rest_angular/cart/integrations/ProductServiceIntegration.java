package com.geekbrains.ru.gb_rest_angular.cart.integrations;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){
        ProductDto forObject = restTemplate.getForObject("http://localhost:8080/app/api/v1/products/" + id, ProductDto.class);
        return Optional.ofNullable(forObject);
    }
}
