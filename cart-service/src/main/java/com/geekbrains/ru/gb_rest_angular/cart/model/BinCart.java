package com.geekbrains.ru.gb_rest_angular.cart.model;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.cart.integrations.ProductServiceIntegration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
public class BinCart {
    List<ProductForBin> productsForBin = new ArrayList<>();
    private BigDecimal totalPrice;

    public List<ProductForBin> getProducts (){
        return Collections.unmodifiableList(productsForBin);
    }

    private void recalculate (){
        totalPrice = BigDecimal.ZERO;
        for (ProductForBin product :productsForBin) {
            product.countPrice();
             totalPrice = totalPrice.add(product.getPrice());
        }
    }

    public void add (ProductDto productDto){
        ProductForBin productForBin = new ProductForBin();
        productForBin.setId(productDto.getId());
        productForBin.setTitle(productDto.getTitle());
        productForBin.setPricePerProduct(productDto.getCost());
        Optional<ProductForBin> first = productsForBin.stream().filter(p -> p.getTitle().equals(productForBin.getTitle())).findFirst();
        if(first.isPresent()){
            first.get().setQuantity(first.get().getQuantity() + 1);
        } else {
            productForBin.setQuantity(1);
            productsForBin.add(productForBin);
        }
        recalculate();
    }

    public void delete (Long id){
        Optional<ProductForBin> first = productsForBin.stream().filter(p -> p.getId().equals(id)).findFirst();
        first.ifPresent(p-> productsForBin.remove(p));
        recalculate();
    }

    public void clear (){
        productsForBin.clear();
        totalPrice = BigDecimal.ZERO;
       // recalculate();
    }

    public void changeScore(Long id, String mark){
        Optional<ProductForBin> first = productsForBin.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (mark.equals("-")){
            ProductForBin productForBin = first.get();
            productForBin.setQuantity(productForBin.getQuantity()-1);
            if (productForBin.getQuantity() <=0) {
                delete(id);
            }
        } else if (mark.equals("+")){
            ProductForBin productForBin = first.get();
            productForBin.setQuantity(productForBin.getQuantity()+1);
        }
        recalculate();
    }
}
