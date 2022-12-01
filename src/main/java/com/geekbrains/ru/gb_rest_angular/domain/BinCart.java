package com.geekbrains.ru.gb_rest_angular.domain;

import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BinCart {

    List<ProductForBin> productsForBin = new ArrayList<>();
    private Integer totalPrice;

    public List<ProductForBin> getProducts (){
        return Collections.unmodifiableList(productsForBin);
    }

    private void recalculate (){
        totalPrice = 0;
        for (ProductForBin product :productsForBin) {
            product.countPrice();
             totalPrice += product.getPrice();
        }
    }

    public void add (ProductDto productDto){
        ProductForBin productForBin = new ProductForBin();
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

    public void delete (String name){
        Optional<ProductForBin> first = productsForBin.stream().filter(p -> p.getTitle().equals(name)).findFirst();
        first.ifPresent(p-> productsForBin.remove(p));
        recalculate();
    }

    public void clear (){
        productsForBin.clear();
        recalculate();
    }

    public void changeScore(String name, String mark){
        Optional<ProductForBin> first = productsForBin.stream().filter(p -> p.getTitle().equals(name)).findFirst();
        if (mark.equals("-")){
            ProductForBin productForBin = first.get();
            productForBin.setQuantity(productForBin.getQuantity()-1);
            if (productForBin.getQuantity() <=0) {
                delete(name);
            }
        } else if (mark.equals("+")){
            ProductForBin productForBin = first.get();
            productForBin.setQuantity(productForBin.getQuantity()+1);
        }
        recalculate();
    }
}
