package com.geekbrains.ru.gb_rest_angular.core.dto;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    public Long id;
    private Long userId;
    private List<OrderItem> items;
    public Integer cost;
    private String address;
    private String phone;


}
