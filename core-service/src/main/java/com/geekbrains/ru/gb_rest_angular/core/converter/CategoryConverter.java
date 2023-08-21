package com.geekbrains.ru.gb_rest_angular.core.converter;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.core.domain.Category;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category dtoToEntity (CategoryDto categoryDto){
        return new Category(categoryDto.getId(), categoryDto.getTitle(), categoryDto.getParentId());
    }

    public CategoryDto entityToDto (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setParentId(category.getParentId());
        categoryDto.setTitle(category.getTitle());
        return categoryDto;
    }
}
