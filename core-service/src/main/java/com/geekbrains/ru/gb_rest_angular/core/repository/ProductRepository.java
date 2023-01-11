package com.geekbrains.ru.gb_rest_angular.core.repository;


import com.geekbrains.ru.gb_rest_angular.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Optional<Product> findFirstByTitle(String title);

    List<Product> findAllByCostBetween(int min, int max );

}
