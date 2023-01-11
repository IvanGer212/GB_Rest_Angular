package com.geekbrains.ru.gb_rest_angular.core.integrations;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    public BinCartDto getCurrentCart(){
        return restTemplate.getForObject("http://localhost:8081/app/api/v1/cart/", BinCartDto.class);
    }
}
