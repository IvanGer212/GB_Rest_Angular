package com.geekbrains.ru.gb_rest_angular.api;

import java.math.BigDecimal;

public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal cost;
    //private String category;
    private String category;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal cost, String category){//String category) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
