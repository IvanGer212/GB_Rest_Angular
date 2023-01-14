package com.geekbrains.ru.gb_rest_angular.core.integrations;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;
    //private final RestTemplate restTemplate;

    public BinCartDto getCurrentCart(){
        BinCartDto binCartDto = cartServiceWebClient.get()
                .uri("/api/v1/cart")
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Корзина не найдена в МС корзины"))
                )
                .bodyToMono(BinCartDto.class)
                .block();
        return binCartDto;
     //   return restTemplate.getForObject("http://localhost:8081/app/api/v1/cart/", BinCartDto.class);
    }
}
