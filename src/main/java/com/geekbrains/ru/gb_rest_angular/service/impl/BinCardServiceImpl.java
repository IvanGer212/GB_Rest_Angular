package com.geekbrains.ru.gb_rest_angular.service.impl;

import com.geekbrains.ru.gb_rest_angular.domain.BinCard;
import com.geekbrains.ru.gb_rest_angular.domain.ProductForBin;
import com.geekbrains.ru.gb_rest_angular.dto.ProductDto;
import com.geekbrains.ru.gb_rest_angular.service.BinCardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class BinCardServiceImpl implements BinCardService {

    private BinCard binCard;

    public BinCardServiceImpl (BinCard binCard){this.binCard = binCard;}

    @Override
    public void addProductOnBin(ProductDto productDto) {
        ProductForBin productForBin = new ProductForBin();
        productForBin.setTitle(productDto.getTitle());
        productForBin.setPrice(productDto.getCost());
        Optional<ProductForBin> first = binCard.getProductsForBin().stream().filter(p -> p.getTitle().equals(productForBin.getTitle())).findFirst();
        if (first.isPresent()) {
            first.get().setQuantity(first.get().getQuantity() + 1);
        } else {
            productForBin.setQuantity(1);
            binCard.getProductsForBin().add(productForBin);
        }
    }

    @Override
    public List<ProductForBin> findAllProductOnBin() {
        List<ProductForBin> productsForBin = binCard.getProductsForBin();
        if (productsForBin.isEmpty()){
            return new ArrayList<>();
        }
        else
            return productsForBin;
    }

    @Override
    public void deleteProductOnBin(String name) {
        Optional<ProductForBin> first = binCard.getProductsForBin().stream().filter(p -> p.getTitle().equals(name)).findFirst();
        first.ifPresent(p->binCard.getProductsForBin().remove(p));
    }
}
