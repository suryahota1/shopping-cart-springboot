package com.java_learning.shopping_cart.request;

import com.java_learning.shopping_cart.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int inventory;
    private Category category;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getInventory() {
        return inventory;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
