package com.geekbrains.ru.gb_rest_angular.domain;

import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@Component
public class BinCard {

    List<ProductForBin> productsForBin;

}
