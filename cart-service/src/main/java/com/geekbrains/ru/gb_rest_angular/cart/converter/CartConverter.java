package com.geekbrains.ru.gb_rest_angular.cart.converter;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
import com.geekbrains.ru.gb_rest_angular.cart.model.BinCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final ProductForBinConverter productForBinConverter;

    public BinCartDto modelToDto (BinCart binCart){
        BinCartDto binCartDto = new BinCartDto();
        binCartDto.setProductsForBin(binCart.getProducts().stream().map(productForBinConverter::modelToDto).collect(Collectors.toList()));
        binCartDto.setTotalPrice(binCart.getTotalPrice());
        return binCartDto;
    }
}
