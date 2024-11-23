package com.java_learning.shopping_cart.service.category;

import com.java_learning.shopping_cart.exceptions.CategoryNotFoundException;
import com.java_learning.shopping_cart.exceptions.ResourceAlreadyExistsException;
import com.java_learning.shopping_cart.model.Category;
import com.java_learning.shopping_cart.repository.CategoryRepository;
import com.java_learning.shopping_cart.request.UpdateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    CategoryService ( CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter((c) -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository :: save)
                .orElseThrow(() -> new ResourceAlreadyExistsException(category.getName() + " already exists"));
    }

    @Override
    public Category updateCategory(UpdateCategoryRequest category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(( existingCategory ) -> {
            existingCategory.setName(category.getName());
            return categoryRepository.save(existingCategory);
        }).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository :: delete, () -> {
            throw new CategoryNotFoundException("Category not found");
        });
    }
}
