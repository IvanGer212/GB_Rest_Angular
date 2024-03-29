package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.core.converter.ProductConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Category;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.dto.PageDto;
import com.geekbrains.ru.gb_rest_angular.core.exception.ValidationException;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import com.geekbrains.ru.gb_rest_angular.core.validators.ProductValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest
class ProductControllerTest {

    @MockBean
    private ProductService productService;
    @MockBean
    private ProductConverter productConverter;
    @MockBean
    private ProductValidator productValidator;

    @InjectMocks
    private ProductController productController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getProductById() throws Exception {
        Product product = new Product(1L, "Product 1", BigDecimal.valueOf(30), new Category());
        ProductDto productDto = new ProductDto(1l,"Product 2", BigDecimal.valueOf(35),"Food");
        when(productService.findProductById(1l)).thenReturn(Optional.of(product));
        when(productConverter.entityToDto(product)).thenReturn(productDto);

        mockMvc.perform(get("/app/api/v1/products/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

//    @Test
//    void addProduct() {
//    }
//
    @Test
    void updateProduct() throws Exception {
        ProductDto productDto = new ProductDto(1l,null, BigDecimal.valueOf(35),"Food");
        Product product = new Product(1L, "Product 1", BigDecimal.valueOf(30), new Category());
        when(productService.update(any())).thenReturn(product);
        when(productConverter.entityToDto(product)).thenReturn(productDto);

        mockMvc.perform(put("/app/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(productDto)))
                .andExpect(status().isOk());
    }

    @Test
    void getProductsByCategoryId() throws Exception{
        Product product = new Product(1L, "Product 1", BigDecimal.valueOf(30), new Category());
        List<Product> products = Collections.singletonList(product);
        ProductDto productDto = new ProductDto(1l,"Product 2", BigDecimal.valueOf(35),"Food");

        Mockito.doReturn(products).when(productService).findProductsByCategoryId(2l);
        Mockito.doReturn(productDto).when(productConverter).entityToDto(any());

        mockMvc.perform(get("/app/api/v1/products/by_category").param("id","2")).andExpect(status().isOk());
    }

    @Test
    void getProducts() throws Exception {
        Product product = new Product(1L, "Product 1", BigDecimal.valueOf(30), new Category());
        List<Product> products = Collections.singletonList(product);
        Page<Product> productPage = new PageImpl<>(products);

        ProductDto productDto = new ProductDto(1L, "Product 1", BigDecimal.valueOf(30), "Food");
        List<ProductDto> productDtos = Collections.singletonList(productDto);
        PageDto<ProductDto> pageDto = new PageDto<>();
        pageDto.setItems(productDtos);
        pageDto.setPage(1);

        when(productService.find(anyInt(),anyInt(), anyString(), anyInt(), anyLong())).thenReturn(productPage);
        Mockito.doReturn(productDto).when(productConverter).entityToDto(any());

        mockMvc.perform(get("/app/api/v1/products").contentType(MediaType.APPLICATION_JSON).param("p", "1").param("min_price", "20").param("max_price", "50").param("title", "Product 1").param("category", "1"))
                .andExpect(status().isOk());

    }


}