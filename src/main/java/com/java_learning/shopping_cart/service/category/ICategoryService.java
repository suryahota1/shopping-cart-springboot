package com.java_learning.shopping_cart.service.category;

import com.java_learning.shopping_cart.model.Category;
import com.java_learning.shopping_cart.request.UpdateCategoryRequest;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(UpdateCategoryRequest category, Long id);
    void deleteCategory(Long id);
}
