package com.geekbrains.ru.gb_rest_angular.cart.service.impl;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.cart.integrations.ProductServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.cart.model.BinCart;
import com.geekbrains.ru.gb_rest_angular.cart.service.BinCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class BinCartServiceImpl implements BinCartService {
    private HashMap<String, BinCart> carts;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init(){
        carts = new HashMap<>();
    }

    @Override
    public void addProductOnBin(String uuid, Long id) {
        ProductDto product = productServiceIntegration.getProductById(id); //.orElseThrow(()->new ResourceNotFoundException("Не удалось добавить продукт с id: " + id + " в корзину. Продукт не найден."));
        getCurrentCart(uuid).add(product);
    }

    @Override
    public BinCart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (! carts.containsKey(targetUuid)){
            carts.put(targetUuid, new BinCart());
        }
        return carts.get(targetUuid);
    }

    @Override
    public void deleteProductOnBin(String uuid, Long id) {
        getCurrentCart(uuid).delete(id);
    }

    @Override
    public void clearCart(String uuid){
        getCurrentCart(uuid).clear();
    }

    @Override
    public void changeScore(String uuid, Long id, String mark) {
        getCurrentCart(uuid).changeScore(id, mark);
    }
}
