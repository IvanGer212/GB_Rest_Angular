package com.geekbrains.ru.gb_rest_angular.cart.controller;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
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

    @GetMapping("/{uuid}")
    public BinCartDto findAllProductFromBin(@RequestParam(name = "email", required = false) String username, @PathVariable String uuid){
        String targetUUid = getCartUuid(username,uuid);
        BinCart allProductOnBin = binCartService.getCurrentCart(targetUUid);
        BinCartDto binCartDto = cartConverter.modelToDto(allProductOnBin);
        return binCartDto;
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addProductToBin(@RequestParam(name = "email", required = false) String username, @PathVariable String uuid, @PathVariable Long id){
        String targetUUid = getCartUuid(username,uuid);
        binCartService.addProductOnBin(targetUUid, id);
    }

    @GetMapping("/{uuid}/delete/{id}")
    public void deleteProductOnBin(@RequestParam(name = "email", required = false) String username, @PathVariable String uuid, @PathVariable Long id){
        String targetUUid = getCartUuid(username,uuid);
        binCartService.deleteProductOnBin(targetUUid, id);
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart (@RequestParam(name = "email", required = false) String username, @PathVariable String uuid){
        String targetUUid = getCartUuid(username,uuid);
        binCartService.clearCart(targetUUid);}

    @GetMapping("/{uuid}/change_score")
    public void changeScore(@RequestParam(name = "email", required = false) String username,
                            @PathVariable String uuid,
                            @RequestParam (name = "id") Long id,
                            @RequestParam (name = "mark") String mark) {
        String targetUUid = getCartUuid(username,uuid);
        binCartService.changeScore(targetUUid, id, mark);
    }

    private String getCartUuid(String username, String uuid){
        if (username != null){
            return username;
        }
        else return uuid;
    }
}
