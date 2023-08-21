package com.geekbrains.ru.gb_rest_angular.core.service;

import com.geekbrains.ru.gb_rest_angular.core.domain.Category;
import com.geekbrains.ru.gb_rest_angular.core.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    List<CategoryDto> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    Optional<Category> findCategoryByName(String name);
}
