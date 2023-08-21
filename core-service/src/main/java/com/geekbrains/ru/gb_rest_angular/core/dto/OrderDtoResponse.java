package com.geekbrains.ru.gb_rest_angular.core.dto;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoResponse {

        public Long id;
        private String username;
        public BigDecimal cost;
        private String address;
        private String phone;
        private LocalDate createdAt;

}
