package com.assignment.musinsacatalog.infrastructure.category;

import com.assignment.musinsacatalog.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.deleted = false")
    List<Category> findAllCategories();

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.deleted = false")
    Optional<Category> findCategoryByName(String name);
}
