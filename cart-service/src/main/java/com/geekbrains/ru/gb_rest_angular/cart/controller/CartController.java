package com.geekbrains.ru.gb_rest_angular.cart.controller;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.cart.converter.CartConverter;
import com.geekbrains.ru.gb_rest_angular.cart.model.BinCart;
import com.geekbrains.ru.gb_rest_angular.cart.service.BinCartService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/api/v1/cart")
@CrossOrigin("*")
public class CartController {
    private final CartConverter cartConverter;
    private final BinCartService binCartService;

    @GetMapping
    public BinCartDto findAllProductFromBin(){
        BinCart allProductOnBin = binCartService.findAllProductOnBin();
        BinCartDto binCartDto = cartConverter.modelToDto(allProductOnBin);
        return binCartDto;
    }

    @GetMapping("/add/{id}")
    public void addProductToBin(@PathVariable Long id){
        binCartService.addProductOnBin(id);
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
