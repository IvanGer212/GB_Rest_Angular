package com.geekbrains.ru.gb_rest_angular.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForBin {
    private Long id;
    private String title;
    private BigDecimal pricePerProduct;
    private Integer quantity;
    private BigDecimal price;

    public void countPrice (){
        price = BigDecimal.valueOf(0);
        price = price.add(pricePerProduct.multiply(BigDecimal.valueOf(quantity)));
    }
}
