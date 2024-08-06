package com.assignment.musinsacatalog.application.category;

import com.assignment.musinsacatalog.domain.category.CategoryCommand;
import com.assignment.musinsacatalog.domain.category.CategoryInfo;
import com.assignment.musinsacatalog.domain.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryService categoryService;

    public CategoryInfo.Main registerCategory(CategoryCommand.RegisterCategory command) {

        return categoryService.registerCategory(command);
    }

    public CategoryInfo.Main modifyCategory(CategoryCommand.ModifyCategory command) {

        return categoryService.modifyCategory(command);
    }

    public void removeCategory(Long id) {

        categoryService.removeCategory(id);
    }

    public CategoryInfo.Main getCategory(Long id) {

        return categoryService.getCategory(id);
    }

    public List<CategoryInfo.Main> getCategories() {

        return categoryService.getCategories();
    }
}
