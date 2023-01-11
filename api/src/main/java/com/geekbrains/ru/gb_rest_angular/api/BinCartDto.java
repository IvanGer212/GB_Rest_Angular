package com.geekbrains.ru.gb_rest_angular.api;

import java.util.ArrayList;
import java.util.List;


public class BinCartDto {
    List<ProductForBinDto> productsForBin = new ArrayList<>();
    private Integer totalPrice;


    public List<ProductForBinDto> getProductsForBin() {
        return productsForBin;
    }

    public void setProductsForBin(List<ProductForBinDto> productsForBin) {
        this.productsForBin = productsForBin;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }







}
