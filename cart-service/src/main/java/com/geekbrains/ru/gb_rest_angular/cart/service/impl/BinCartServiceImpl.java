package com.geekbrains.ru.gb_rest_angular.cart.service.impl;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.cart.integrations.ProductServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.cart.model.BinCart;
import com.geekbrains.ru.gb_rest_angular.cart.service.BinCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class BinCartServiceImpl implements BinCartService {
    private BinCart binCart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init(){
        binCart = new BinCart();
    }

    @Override
    public void addProductOnBin(Long id) {
        ProductDto product = productServiceIntegration.getProductById(id); //.orElseThrow(()->new ResourceNotFoundException("Не удалось добавить продукт с id: " + id + " в корзину. Продукт не найден."));
        binCart.add(product);
    }

    @Override
    public BinCart findAllProductOnBin() {
        return binCart;
    }

    @Override
    public void deleteProductOnBin(Long id) {
        binCart.delete(id);
    }

    @Override
    public void clearCart(){
        binCart.clear();
    }

    @Override
    public void changeScore(Long id, String mark) {
        binCart.changeScore(id, mark);
    }
}
