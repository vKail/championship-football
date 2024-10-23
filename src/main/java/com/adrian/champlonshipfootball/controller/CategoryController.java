package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Category;
import com.adrian.champlonshipfootball.service.CategoryService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() throws Exception {
        try {
            return categoryService.findAllCategories();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) throws Exception {
        try {
            return categoryService.findCategoryById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/categories")
    public Category saveCategory(@RequestBody Category category) throws Exception {
        try {
            return categoryService.saveCategory(category);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) throws Exception {
        try {
            return categoryService.updateCategory(id, category);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id) throws Exception {
        try {
            categoryService.deleteCategory(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
