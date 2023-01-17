package com.geekbrains.ru.gb_rest_angular.core.test;

import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.repository.ProductRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;
    private Object ResourceNotFoundException;

    @Test
    public void getAllProductTest(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(69L,"Milk",60));
        products.add(new Product(86L,"Chocolate", 120));
        products.add(new Product(752L,"Apple",87));

        Mockito.doReturn(products).when(productRepository).findAll();

        List<Product> productList = productService.getAllProduct();

        Assertions.assertEquals(productList.size(),3);
        Assertions.assertTrue(productList.contains(new Product(752L,"Apple",87)));
        Assertions.assertEquals(productList.stream().filter(p->p.getId().equals(69L)).findFirst(), Optional.of(new Product(69L,"Milk",60)));
        Assertions.assertArrayEquals(productList.toArray(),products.toArray());

        Mockito.verify(productRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void findProductByIdTest(){
        Product product = new Product(1683L,"Cheese",650);

        Mockito.doReturn(Optional.of(product)).when(productRepository).findById(1683L);
        Mockito.doThrow(new ResourceNotFoundException("Product not found")).when(productRepository).findById(1258L);
        Assertions.assertEquals(new Product(1683L,"Cheese",650),productService.findProductById(1683L).get());
        //Assertions.assertThrows(ResourceNotFoundException, new ResourceNotFoundException("Product not found"));
    }
}
