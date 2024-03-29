package com.geekbrains.ru.gb_rest_angular.core.service.impl;

import com.geekbrains.ru.gb_rest_angular.core.converter.CategoryConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Category;
import com.geekbrains.ru.gb_rest_angular.core.dto.CategoryDto;
import com.geekbrains.ru.gb_rest_angular.core.repository.CategoryRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public List<CategoryDto> findAllCategories() {
        List<CategoryDto> collect = categoryRepository.findAll().stream().map(category -> categoryConverter.entityToDto(category)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findFirstByTitle(name);
    }
}
