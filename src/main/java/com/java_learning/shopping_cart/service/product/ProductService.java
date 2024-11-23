package com.java_learning.shopping_cart.service.product;

import com.java_learning.shopping_cart.exceptions.ProductNotFoundException;
import com.java_learning.shopping_cart.model.Category;
import com.java_learning.shopping_cart.model.Product;
import com.java_learning.shopping_cart.repository.CategoryRepository;
import com.java_learning.shopping_cart.repository.ProductRepository;
import com.java_learning.shopping_cart.request.AddProductRequest;
import com.java_learning.shopping_cart.request.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService (ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request));
    }

    private Product createProduct (AddProductRequest request) {
        return new Product(
            request.getName(),
            request.getBrand(),
            request.getDescription(),
            request.getPrice(),
            request.getInventory(),
            request.getCategory()
        );
    }

    @Override
    public Product updateProduct(UpdateProductRequest product, Long id) {
        return productRepository.findById(id)
            .map(existingProduct -> updateExistingProduct(existingProduct, product))
                .map(productRepository :: save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    private Product updateExistingProduct ( Product existingProduct, UpdateProductRequest newProductData ) {
        existingProduct.setName(newProductData.getName());
        existingProduct.setBrand(newProductData.getBrand());
        existingProduct.setDescription(newProductData.getDescription());
        existingProduct.setPrice(newProductData.getPrice());
        existingProduct.setInventory(newProductData.getInventory());

        Category category = categoryRepository.findByName(newProductData.getCategory().getName());
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id).
            ifPresentOrElse(
                productRepository::delete,
                () -> { throw new ProductNotFoundException("Product not found"); }
            );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String categoryName, String brandName) {
        return productRepository.findByCategoryNameAndBrand(categoryName, brandName);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
