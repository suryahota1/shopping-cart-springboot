package com.java_learning.shopping_cart.repository;

import com.java_learning.shopping_cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByName(String name);

    List<Product> findByBrandAndName(String brand, String name);

    List<Product> findByCategoryNameAndBrand(String categoryName, String brand);

    Long countByBrandAndName(String brand, String name);
}
