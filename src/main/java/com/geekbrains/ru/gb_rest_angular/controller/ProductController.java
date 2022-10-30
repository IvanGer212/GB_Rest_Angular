package com.geekbrains.ru.gb_rest_angular.controller;

import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import com.geekbrains.ru.gb_rest_angular.exception.ErrorResponse;
import com.geekbrains.ru.gb_rest_angular.exception.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam (name="p", defaultValue = "1") Integer page,
                                        @RequestParam (name = "min_price", required = false) Integer minPrice,
                                        @RequestParam (name = "max_price", required = false) Integer maxPrice,
                                        @RequestParam (name = "title", required = false) String title) {
        if (page<1){
            page = 1;
        }
        Page<ProductDto> products = productService.find(minPrice,maxPrice,title,page).map(p-> new ProductDto(p));
        return products;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id){
        return productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found! id= "+id));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
            productService.deleteProductById(id);
    }


    @PostMapping
    public Product addProduct(@RequestBody Product newProduct) {
        Optional<ErrorResponse> validationError = validationNewProduct(newProduct);
        if(validationError.isPresent()){

          return newProduct;
        }
        return productService.addNewProduct(newProduct);
        //return "redirect:/product";
    }


    private Optional<ErrorResponse> validationNewProduct (Product newProduct){
        List<String> details = new ArrayList<>();
        if (newProduct.getTitle().isEmpty()){
            details.add("Product name could not be empty!");
        }
        if (newProduct.getCost() <= 0){
            details.add("Price could not be less or equal 0!");
        }
        if (details.size()!=0){
            return Optional.of(new ErrorResponse("Uncorrect Product!",details));
        }
        return Optional.empty();
    }
}

