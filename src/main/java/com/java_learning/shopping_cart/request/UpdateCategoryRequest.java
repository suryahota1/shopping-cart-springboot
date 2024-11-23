package com.java_learning.shopping_cart.request;

import com.java_learning.shopping_cart.model.Product;

import java.util.List;

public class UpdateCategoryRequest {
    private Long id;
    private String name;
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
