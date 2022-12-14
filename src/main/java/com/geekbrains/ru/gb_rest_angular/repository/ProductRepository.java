package com.geekbrains.ru.gb_rest_angular.repository;


import com.geekbrains.ru.gb_rest_angular.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findFirstByTitle(String title);

    List<Product> findAllByCostBetween(int min, int max );

}
