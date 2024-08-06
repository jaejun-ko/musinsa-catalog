package com.assignment.musinsacatalog.domain.category;

import java.util.List;

public interface CategoryReader {

    Category getCategory(Long id);

    List<Category> getCategories();

    Category getCategoryByName(String name);
}
