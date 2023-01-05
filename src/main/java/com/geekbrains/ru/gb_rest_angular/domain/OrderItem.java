package com.geekbrains.ru.gb_rest_angular.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_per_product")
    private Integer pricePerProduct;

    @Column(name = "cost")
    private Integer costOrder;

    public OrderItem(Product product, Order order, Integer quantity, Integer pricePerProduct, Integer costOrder) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.costOrder = costOrder;
    }
}
