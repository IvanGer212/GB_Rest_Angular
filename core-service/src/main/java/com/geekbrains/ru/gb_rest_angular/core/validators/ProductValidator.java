package com.geekbrains.ru.gb_rest_angular.core.validators;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.core.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        String title = productDto.getTitle();
        BigDecimal cost = productDto.getCost();
        String category = productDto.getCategory();

        if (cost == null){
            errors.add("Необходимо назначить цену продукту!");
        } else if (cost.doubleValue() <=0 ){
            errors.add("Цена продукта не может быть меньше или равна 0!");
        }

        if (title == null){
            errors.add("Необходимо ввести название товара!");
        } else if (title.isEmpty()){
            errors.add("Название продукта не может быть пустым!");
        }

        if(category == null){
            errors.add("Необходимо выбрать категорию для товара!");
        }

        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
