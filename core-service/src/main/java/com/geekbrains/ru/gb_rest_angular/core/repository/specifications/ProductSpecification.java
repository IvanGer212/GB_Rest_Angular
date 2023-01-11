package com.geekbrains.ru.gb_rest_angular.core.repository.specifications;

import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecification {

    public static Specification<Product> priceGreaterThanOrEqualsThan (Integer price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"),price);
    }

    public static Specification<Product> priceLessThanOrEqualsThan (Integer price){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"),price);
    }

    public static Specification<Product> titleLike (String title){
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"),String.format("%%%s%%",title));
    }

}
