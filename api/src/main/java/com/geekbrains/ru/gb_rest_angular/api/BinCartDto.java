package com.geekbrains.ru.gb_rest_angular.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class BinCartDto {
    List<ProductForBinDto> productsForBin = new ArrayList<>();
    private BigDecimal totalPrice;


    public List<ProductForBinDto> getProductsForBin() {
        return productsForBin;
    }

    public void setProductsForBin(List<ProductForBinDto> productsForBin) {
        this.productsForBin = productsForBin;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }







}
