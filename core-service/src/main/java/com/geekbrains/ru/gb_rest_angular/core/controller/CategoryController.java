package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.core.converter.CategoryConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Category;
import com.geekbrains.ru.gb_rest_angular.core.dto.CategoryDto;
import com.geekbrains.ru.gb_rest_angular.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/app/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @GetMapping
    public List<CategoryDto> getCategories(){
        List<CategoryDto> allCategories = categoryService.findAllCategories();
        return allCategories;
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id){
        Category category;
        Optional<Category> categoryById = categoryService.findCategoryById(id);
        if (categoryById.isPresent()) {
             category = categoryById.get();
        }
        else category = new Category();
        return categoryConverter.entityToDto(category);
    }

    @GetMapping("/find")
    public CategoryDto getCategoryByName(@RequestParam(name = "categoryName") String categoryName){
        Category category;
        Optional<Category> categoryByName = categoryService.findCategoryByName(categoryName);
        if (categoryByName.isPresent()){
            category = categoryByName.get();
        }
        else category = new Category();
        return categoryConverter.entityToDto(category);
    }
}
