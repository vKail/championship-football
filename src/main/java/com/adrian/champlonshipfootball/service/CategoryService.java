package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Category;
import com.adrian.champlonshipfootball.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
    public Category findCategoryById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    public Category updateCategory(long id,Category category) {
        Category categoryToUpdate = findCategoryById(id);
        categoryToUpdate.setCategoryName(category.getCategoryName());
        return categoryRepository.save(categoryToUpdate);
    }
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
