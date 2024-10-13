package com.adrian.champlonshipfootball.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id", nullable = false)
    private long categoryId;
    @Column(name = "category_name", nullable = false)
    private String categoryName;
    @ManyToMany(mappedBy = "categories")
    private Set<Team> teams = new HashSet<>();
    public Category() {}

    public Category(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
