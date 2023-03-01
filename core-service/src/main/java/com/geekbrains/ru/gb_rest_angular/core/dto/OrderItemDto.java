package com.geekbrains.ru.gb_rest_angular.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private String title;
    private Long orderId;
    private Integer quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal costOrder;
}
