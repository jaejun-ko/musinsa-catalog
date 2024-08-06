package com.assignment.musinsacatalog.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryStore categoryStore;
    private final CategoryReader categoryReader;

    @Transactional
    @Override
    public CategoryInfo.Main registerCategory(CategoryCommand.RegisterCategory command) {

        Category category = command.toEntity();

        Category storedCategory = categoryStore.store(category);

        return new CategoryInfo.Main(storedCategory);
    }

    @Transactional
    @Override
    public CategoryInfo.Main modifyCategory(CategoryCommand.ModifyCategory command) {

        Category category = categoryReader.getCategory(command.getId());

        category.changeName(command.getName());

        return new CategoryInfo.Main(category);
    }

    @Transactional
    @Override
    public void removeCategory(Long id) {

        Category category = categoryReader.getCategory(id);

        category.delete();
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryInfo.Main getCategory(Long id) {

        Category category = categoryReader.getCategory(id);

        return new CategoryInfo.Main(category);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryInfo.Main> getCategories() {

        List<Category> categories = categoryReader.getCategories();

        return categories.stream()
                .map(CategoryInfo.Main::new)
                .toList();
    }

    @Override
    public CategoryInfo.Main getCategoryByName(String categoryName) {

        Category category = categoryReader.getCategoryByName(categoryName);

        return new CategoryInfo.Main(category);
    }
}
