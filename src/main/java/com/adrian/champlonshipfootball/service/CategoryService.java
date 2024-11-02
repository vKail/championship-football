package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.CategoryDto;
import com.adrian.champlonshipfootball.model.Category;
import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDto findCategoryById(long id) {
        return categoryRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public CategoryDto saveCategory(CategoryDto categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    public CategoryDto updateCategory(long id, CategoryDto categoryDTO) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        updateCategoryFromDTO(existingCategory, categoryDTO);
        Category updatedCategory = categoryRepository.save(existingCategory);
        return convertToDTO(updatedCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private CategoryDto convertToDTO(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        dto.setAgeMin(category.getAgeMin());
        dto.setAgeMax(category.getAgeMax());

        if (category.getTeams() != null) {
            dto.setTeamIds(category.getTeams().stream()
                    .map(team -> ((Team) team).getTeamId())
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    private Category convertToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        category.setCategoryName(dto.getCategoryName());
        category.setAgeMin(dto.getAgeMin());
        category.setAgeMax(dto.getAgeMax());
        return category;
    }

    private void updateCategoryFromDTO(Category category, CategoryDto dto) {
        category.setCategoryName(dto.getCategoryName());
        category.setAgeMin(dto.getAgeMin());
        category.setAgeMax(dto.getAgeMax());
    }
}