package com.geekbrains.ru.gb_rest_angular.core.validators;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.core.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if (productDto.getCost().doubleValue() <=0 ){
            errors.add("Цена продукта не может быть меньше или равна 0!");
        }
        if (productDto.getTitle().isEmpty()){
            errors.add("Название продукта не может быть пустым!");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
