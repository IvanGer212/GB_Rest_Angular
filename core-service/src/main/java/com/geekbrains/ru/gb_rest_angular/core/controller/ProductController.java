package com.geekbrains.ru.gb_rest_angular.core.controller;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.core.converter.ProductConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.dto.PageDto;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import com.geekbrains.ru.gb_rest_angular.core.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/api/v1/products")
//@AllArgsConstructor
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;


    @GetMapping
    public PageDto<ProductDto> getProducts(@RequestParam (name="p", defaultValue = "1") Integer page,
                                        @RequestParam (name = "min_price", required = false) Integer minPrice,
                                        @RequestParam (name = "max_price", required = false) Integer maxPrice,
                                        @RequestParam (name = "title", required = false) String title,
                                        @RequestParam (name = "category", required = false) Long categoryId) {
        if (page<1){
            page = 1;
        }

        Page<Product> products1 = productService.find(minPrice, maxPrice, title, page, categoryId);
        Page<ProductDto> products = products1.map(p-> productConverter.entityToDto(p));
        PageDto<ProductDto> out = new PageDto<>();
        out.setPage(products.getNumber());
        out.setItems(products.getContent());
        out.setTotalPages(products.getTotalPages());
        return out;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found! id= "+id));
        System.out.println(product);
        ProductDto dto = productConverter.entityToDto(product);
        System.out.println(dto.toString());
        return dto;
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
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @GetMapping("/by_category")
    public List<ProductDto> getProductsByCategoryId(@RequestParam(name = "id") Long categoryId){
        List<Product> productsByCategoryId = productService.findProductsByCategoryId(categoryId);
        List<ProductDto> collect = productsByCategoryId.stream().map(product -> productConverter.entityToDto(product)).collect(Collectors.toList());
        return collect;
    }


}

