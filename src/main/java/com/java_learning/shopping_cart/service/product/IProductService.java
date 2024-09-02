package com.java_learning.shopping_cart.service.product;

import com.java_learning.shopping_cart.model.Category;
import com.java_learning.shopping_cart.model.Product;
import com.java_learning.shopping_cart.request.AddProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);
    Product updateProduct(Product product, Long id);
    Product getProductById(Long id);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String categoryName);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName);
    List<Product> getProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand, String name);
}
