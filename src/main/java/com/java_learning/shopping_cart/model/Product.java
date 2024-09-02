package com.java_learning.shopping_cart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String brand;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private BigDecimal price;

    @Getter @Setter
    private int inventory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Product(String name, String brand, String description, BigDecimal price, int inventory, Category category) {
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
    }
}
