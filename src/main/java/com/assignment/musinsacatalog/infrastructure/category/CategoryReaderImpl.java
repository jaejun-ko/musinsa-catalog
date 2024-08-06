package com.assignment.musinsacatalog.infrastructure.category;

import com.assignment.musinsacatalog.common.exception.EntityNotFoundException;
import com.assignment.musinsacatalog.domain.category.Category;
import com.assignment.musinsacatalog.domain.category.CategoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryReaderImpl implements CategoryReader {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Category> getCategories() {

        return categoryRepository.findAllCategories();
    }

    @Override
    public Category getCategoryByName(String name) {

        return categoryRepository.findCategoryByName(name)
                .orElseThrow(EntityNotFoundException::new);
    }
}
