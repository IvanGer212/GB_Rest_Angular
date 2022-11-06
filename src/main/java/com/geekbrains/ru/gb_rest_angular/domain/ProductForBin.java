package com.geekbrains.ru.gb_rest_angular.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForBin {
    private String title;
    private Integer price;
    private Integer quantity;
}
