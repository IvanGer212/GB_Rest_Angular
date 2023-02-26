package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.core.converter.CategoryConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Category;
import com.geekbrains.ru.gb_rest_angular.core.dto.CategoryDto;
import com.geekbrains.ru.gb_rest_angular.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/app/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @GetMapping
    public List<CategoryDto> getCategories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        Category category;
        if (categoryService.findCategoryById(id).isPresent()) {
             category = categoryService.findCategoryById(id).get();//TODO add categoryConverter
        }
        else category = new Category();
        return categoryConverter.entityToDto(category);
    }

    @GetMapping("/find")
    public CategoryDto getCategoryByName(@RequestParam(name = "categoryName") String categoryName){
        Category category;
        if (categoryService.findCategoryByName(categoryName).isPresent()){
            category = categoryService.findCategoryByName(categoryName).get();
        }
        else category = new Category();
        return categoryConverter.entityToDto(category);
    }
}
