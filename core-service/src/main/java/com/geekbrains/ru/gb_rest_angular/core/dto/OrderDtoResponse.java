package com.geekbrains.ru.gb_rest_angular.core.dto;

import com.geekbrains.ru.gb_rest_angular.core.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


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
