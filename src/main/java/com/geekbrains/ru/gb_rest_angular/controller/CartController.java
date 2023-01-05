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

    @GetMapping("/delete/{id}")
    public void deleteProductOnBin(@PathVariable Long id){
        binCartService.deleteProductOnBin(id);
    }

    @GetMapping("/clear")
    public void clearCart (){ binCartService.clearCart();}

    @GetMapping("/change_score")
    public void changeScore(@RequestParam (name = "id") Long id,
                            @RequestParam (name = "mark") String mark) {
        binCartService.changeScore(id, mark);

    }
}
