package com.geekbrains.ru.gb_rest_angular.controller;

import com.geekbrains.ru.gb_rest_angular.domain.BinCart;
import com.geekbrains.ru.gb_rest_angular.domain.ProductForBin;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import com.geekbrains.ru.gb_rest_angular.service.BinCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/cart")
public class CartController {

    private final BinCartService binCartService;

    @GetMapping
    public BinCart findAllProductFromBin(){
        return binCartService.findAllProductOnBin();
    }

    @PostMapping("/add")
    public void addProductToBin(@RequestBody ProductDto productDto){
        binCartService.addProductOnBin(productDto);
    }

    @GetMapping("/delete/{name}")
    public void deleteProductOnBin(@PathVariable String name){
        binCartService.deleteProductOnBin(name);
    }

    @GetMapping("/clear")
    public void clearCart (){ binCartService.clearCart();}

    @GetMapping("/change_score")
    public void changeScore(@RequestParam (name = "name") String name,
                            @RequestParam (name = "mark") String mark) {
        binCartService.changeScore(name, mark);

    }
}
