package com.geekbrains.ru.gb_rest_angular.controller;

import com.geekbrains.ru.gb_rest_angular.converter.ProductConverter;
import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.domain.ProductForBin;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import com.geekbrains.ru.gb_rest_angular.exception.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.service.BinCartService;
import com.geekbrains.ru.gb_rest_angular.service.ProductService;
import com.geekbrains.ru.gb_rest_angular.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/api/v1/products")
//@AllArgsConstructor
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;


    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam (name="p", defaultValue = "1") Integer page,
                                        @RequestParam (name = "min_price", required = false) Integer minPrice,
                                        @RequestParam (name = "max_price", required = false) Integer maxPrice,
                                        @RequestParam (name = "title", required = false) String title) {
        if (page<1){
            page = 1;
        }
        Page<ProductDto> products = productService.find(minPrice,maxPrice,title,page).map(p-> productConverter.entityToDto(p));
        return products;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found! id= "+id));
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
            productService.deleteProductById(id);
    }


    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto newProductDto) {
        productValidator.validate(newProductDto);
        Product product = productConverter.dtoToEntity(newProductDto);
        product = productService.addNewProduct(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestParam ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }


}


