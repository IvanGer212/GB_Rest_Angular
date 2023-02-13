package com.geekbrains.ru.gb_rest_angular.core.test;

import com.geekbrains.ru.gb_rest_angular.api.BinCartDto;
import com.geekbrains.ru.gb_rest_angular.api.ProductForBinDto;
import com.geekbrains.ru.gb_rest_angular.core.domain.Order;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.domain.User;
import com.geekbrains.ru.gb_rest_angular.core.integrations.CartServiceIntegration;
import com.geekbrains.ru.gb_rest_angular.core.repository.OrderRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.OrderService;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest(){
        BinCartDto cart = new BinCartDto();
        ProductForBinDto product = new ProductForBinDto();
        product.setId(1457L);
        product.setTitle("Milk");
        product.setPricePerProduct(BigDecimal.valueOf(60));
        product.setQuantity(2);
        product.setPrice(BigDecimal.valueOf(120));

        cart.setTotalPrice(BigDecimal.valueOf(120));
        cart.setProductsForBin(List.of(product));

        Mockito.doReturn(cart).when(cartServiceIntegration).getCurrentCart("BobBlack76@mail.ru");

        Product product1 = new Product();
        product1.setId(1457L);
        product1.setTitle("Milk");
        product1.setCost(BigDecimal.valueOf(60));

        Mockito.doReturn(Optional.of(product1)).when(productService).findProductById(1457L);

        User user = new User();
        user.setUserName("Bob");
        user.setSurname("Black");
        user.setEmail("BobBlack76@mail.ru");
        user.setPhone("+726552556");
        Order order = orderService.createOrder(user);
        Assertions.assertEquals(order.getCost(),120);
        Mockito.verify(orderRepository,Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
