package com.geekbrains.ru.gb_rest_angular.controller;


import com.geekbrains.ru.gb_rest_angular.domain.Limits;
import com.geekbrains.ru.gb_rest_angular.domain.Product;
import com.geekbrains.ru.gb_rest_angular.exception.ErrorResponse;
import com.geekbrains.ru.gb_rest_angular.exception.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping("/product")
    public List<Product> getProducts(){
        List<Product> products = productService.getAllProduct();
//        model.addAttribute("products",products);
//        model.addAttribute("Limits", new Limits());
        return products;
    }


    @GetMapping("/product-between")
    public List<Product> getProductBetweenCost(@RequestParam (defaultValue = "0") int min, @RequestParam(defaultValue = "1000000") int max){
        List<Product> allByCostBetween = productService.findAllByCostBetween(min, max);
        return allByCostBetween;
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id){
        return productService.findProductById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found! id= "+id));
    }

//    public String createAddProductPage(Model model){
//    @GetMapping("/create-product")
//        model.addAttribute("newProduct", new Product());
//
//        return "create-product";
//    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
//        Optional<Product> productById = productService.findProductById(id);
//        if (productById.isPresent()) {
            productService.deleteProductById(id);
//        return "redirect:/product";}
//        else {
//            throw new ResourceNotFoundException("Product could not be delete. Product not found. id=" + id);
//        }
//        }
    }


    @PostMapping("/createProduct")
    public Product addProduct(@RequestBody Product newProduct) {
//        Optional<ErrorResponse> validationError = validationNewProduct(newProduct);
//        if(validationError.isPresent()){
//            model.addAttribute("error", validationError.get());
        //    return "exception-page";
//        }
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

