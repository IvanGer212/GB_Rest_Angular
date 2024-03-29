package com.geekbrains.ru.gb_rest_angular.core.test;

import com.geekbrains.ru.gb_rest_angular.api.ProductDto;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import com.geekbrains.ru.gb_rest_angular.core.converter.CategoryConverter;
import com.geekbrains.ru.gb_rest_angular.core.domain.Category;
import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import com.geekbrains.ru.gb_rest_angular.core.dto.CategoryDto;
import com.geekbrains.ru.gb_rest_angular.core.repository.ProductRepository;
import com.geekbrains.ru.gb_rest_angular.core.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

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
        Category category = new Category();
        products.add(new Product(69L,"Milk", BigDecimal.valueOf(60), category));
        products.add(new Product(86L,"Chocolate", BigDecimal.valueOf(120), category));
        products.add(new Product(752L,"Apple",BigDecimal.valueOf(87), category));

        Mockito.doReturn(products).when(productRepository).findAll();

        List<Product> productList = productService.getAllProduct();

        Assertions.assertEquals(productList.size(),3);
        Assertions.assertTrue(productList.contains(new Product(752L,"Apple",BigDecimal.valueOf(87),category)));
        Assertions.assertEquals(productList.stream().filter(p->p.getId().equals(69L)).findFirst(), Optional.of(new Product(69L,"Milk",BigDecimal.valueOf(60),category)));
        Assertions.assertArrayEquals(productList.toArray(),products.toArray());

        Mockito.verify(productRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void findProductByIdTest(){
        Product product = new Product(1683L,"Cheese",BigDecimal.valueOf(650),new Category());

        Mockito.doReturn(Optional.of(product)).when(productRepository).findById(1683L);
        Mockito.doThrow(new ResourceNotFoundException("Product not found")).when(productRepository).findById(1258L);

        Assertions.assertEquals(new Product(1683L,"Cheese",BigDecimal.valueOf(650), new Category()),productService.findProductById(1683L).get());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> productService.findProductById(1258L));
    }

    @Test
    public void addNewProductTest(){
        Product product = new Product(847L, "Milk", BigDecimal.valueOf(784),new Category());
        Product product1 = new Product(794L,"Tea", BigDecimal.valueOf(9541), new Category());

        Mockito.doReturn(Optional.of(product)).when(productRepository).findFirstByTitle(product.getTitle());
        Mockito.doReturn(Optional.empty()).when(productRepository).findFirstByTitle("Tea");
        Mockito.doReturn(new Product(847L, "Milk", BigDecimal.valueOf(784),new Category())).when(productRepository).save(product);
        Mockito.doReturn(new Product(794L, "Tea", BigDecimal.valueOf(9541),new Category())).when(productRepository).save(product1);

        Assertions.assertEquals(product, productService.addNewProduct(product));
        Assertions.assertEquals(product1, productService.addNewProduct(product1));
        Mockito.verify(productRepository, Mockito.times(1)).save(product1);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void updateTest (){
        ProductDto productDto = new ProductDto(597L,"Window",BigDecimal.valueOf(5921), "furniture");
        ProductDto productDto1 = new ProductDto(599L,"Plane",BigDecimal.valueOf(5981), "furniture");

        Mockito.doReturn(Optional.of(new Product(597L, "Table", BigDecimal.valueOf(847), new Category()))).when(productRepository).findById(productDto.getId());
        Mockito.doReturn(Optional.empty()).when(productRepository).findById(productDto1.getId());

        Assertions.assertEquals(new Product(597L,"Window",BigDecimal.valueOf(5921),new Category()), productService.update(productDto));
        Mockito.verify(productRepository,Mockito.times(1)).findById(productDto.getId());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> productService.update(productDto1));
    }

    @Test
    public void findTest(){
        Page<Product> page;
        CategoryConverter converter = new CategoryConverter();
        CategoryDto categoryDto = new CategoryDto(1L,"food",0L);
        Category category = converter.dtoToEntity(categoryDto);
        CategoryDto categoryDto1 = new CategoryDto(2L, "fruit", 0L);
        Category category1 = converter.dtoToEntity(categoryDto1);

        List<Product> products = new ArrayList<>();
        products.add(new Product(847L, "Milk", BigDecimal.valueOf(78.4), category));
        products.add(new Product(848L, "Lemon", BigDecimal.valueOf(86.3),category1));
        products.add(new Product(849L, "Water", BigDecimal.valueOf(43.1),category));

        List<Product> collect = products.stream().filter(product -> product.getCategory().getId().equals(1L)).collect(Collectors.toList());

        Specification<Product> spec = Specification.where(null);
        Page<Product> page1 = new PageImpl<Product>(products,PageRequest.of(0,5), products.size());
        page = new PageImpl<Product>(collect, PageRequest.of(0,5),products.size());

        Mockito.doReturn(products).when(productRepository).findAll(spec);
        Mockito.doReturn(page1).when(productRepository).findAll(spec,PageRequest.of(0,5));

        Assertions.assertEquals(products, productRepository.findAll(spec));
        Assertions.assertEquals(page, productService.find(null,null, null,1, 1L));
        Assertions.assertEquals(page1, productService.find(null,null,null,1,null));

    }
}
