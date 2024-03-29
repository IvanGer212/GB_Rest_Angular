package com.geekbrains.ru.gb_rest_angular.cart.service.impl;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.cart.integrations.ProductServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.cart.model.BinCart;
import com.geekbrains.ru.gb_rest_angular.cart.service.BinCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class BinCartServiceImpl implements BinCartService {
    private HashMap<String, BinCart> carts;
    private final RedisTemplate<String, Object> redisTemplate;


    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;
    private final ProductServiceIntegration productServiceIntegration;

//    @PostConstruct
//    public void init(){
//        carts = new HashMap<>();
//    }

    @Override
    public void addProductOnBin(String uuid, Long id) {
        ProductDto product = productServiceIntegration.getProductById(id); //.orElseThrow(()->new ResourceNotFoundException("Не удалось добавить продукт с id: " + id + " в корзину. Продукт не найден."));
        execute(uuid, binCart -> binCart.add(product));
//        getCurrentCart(uuid).add(product);
    }

    @Override
    public BinCart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)){
            redisTemplate.opsForValue().set(targetUuid, new BinCart());
        }
        return (BinCart) redisTemplate.opsForValue().get(targetUuid);
//        if (! carts.containsKey(targetUuid)){
//            carts.put(targetUuid, new BinCart());
//        }
//        return carts.get(targetUuid);
    }

    @Override
    public void deleteProductOnBin(String uuid, Long id) {
        execute(uuid, binCart -> binCart.delete(id));
//        getCurrentCart(uuid).delete(id);
    }

    @Override
    public void clearCart(String uuid){
        execute(uuid, binCart -> binCart.clear());
//        getCurrentCart(uuid).clear();
    }

    @Override
    public void changeScore(String uuid, Long id, String mark) {
        execute(uuid, binCart -> binCart.changeScore(id, mark));
//        getCurrentCart(uuid).changeScore(id, mark);
    }

    private void execute(String uuid, Consumer<BinCart> operation) {
        BinCart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }
}
