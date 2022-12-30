package com.geekbrains.ru.gb_rest_angular.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    @Column(name = "cost")
    public Integer cost;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;
}
