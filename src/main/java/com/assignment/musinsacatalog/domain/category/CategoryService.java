package com.assignment.musinsacatalog.domain.category;

import java.util.List;

public interface CategoryService {

    CategoryInfo.Main registerCategory(CategoryCommand.RegisterCategory command);

    CategoryInfo.Main modifyCategory(CategoryCommand.ModifyCategory command);

    void removeCategory(Long id);

    CategoryInfo.Main getCategory(Long id);

    List<CategoryInfo.Main> getCategories();

    CategoryInfo.Main getCategoryByName(String categoryName);
}
