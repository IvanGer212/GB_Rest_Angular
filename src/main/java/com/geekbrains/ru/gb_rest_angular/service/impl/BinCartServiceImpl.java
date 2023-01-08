package com.geekbrains.ru.gb_rest_angular.service.impl;

import com.geekbrains.ru.gb_rest_angular.domain.BinCart;
import com.geekbrains.ru.gb_rest_angular.domain.ProductForBin;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import com.geekbrains.ru.gb_rest_angular.service.BinCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BinCartServiceImpl implements BinCartService {

    private BinCart binCart;

    @PostConstruct
    public void init(){
        binCart = new BinCart();
    }

    public BinCartServiceImpl(BinCart binCart){this.binCart = binCart;}

    @Override
    public void addProductOnBin(ProductDto productDto) {
        binCart.add(productDto);
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
