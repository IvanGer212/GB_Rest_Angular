package com.geekbrains.ru.gb_rest_angular.cart.integrations;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id){
        ProductDto productDto = productServiceWebClient.get()
                .uri("/api/v1/products/"+ id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                )
                .bodyToMono(ProductDto.class)
                .block();
        return productDto;

//        ProductDto forObject = restTemplate.getForObject("http://localhost:8080/app/api/v1/products/" + id, ProductDto.class);
//        return Optional.ofNullable(forObject);
    }

}
