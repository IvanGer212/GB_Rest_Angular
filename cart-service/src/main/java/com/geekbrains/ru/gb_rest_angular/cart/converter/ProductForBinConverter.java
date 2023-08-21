package com.geekbrains.ru.gb_rest_angular.cart.converter;

import com.geekbrains.ru.gb_rest_angular.api.ProductForBinDto;
import com.geekbrains.ru.gb_rest_angular.cart.model.ProductForBin;
import org.springframework.stereotype.Component;

@Component
public class ProductForBinConverter {

    public ProductForBinDto modelToDto(ProductForBin productForBin){
        ProductForBinDto productForBinDto = new ProductForBinDto();
        productForBinDto.setPrice(productForBin.getPrice());
        productForBinDto.setPricePerProduct(productForBin.getPricePerProduct());
        productForBinDto.setTitle(productForBin.getTitle());
        productForBinDto.setQuantity(productForBin.getQuantity());
        productForBinDto.setId(productForBin.getId());
        return productForBinDto;
    }

}
