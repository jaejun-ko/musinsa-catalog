package com.assignment.musinsacatalog.infrastructure.category;

import com.assignment.musinsacatalog.common.exception.InvalidParamException;
import com.assignment.musinsacatalog.domain.category.Category;
import com.assignment.musinsacatalog.domain.category.CategoryStore;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryStoreImpl implements CategoryStore {

    private final CategoryRepository categoryRepository;

    @Override
    public Category store(Category category) {

        if (StringUtils.isBlank(category.getName())) throw new InvalidParamException("category.getName()");

        return categoryRepository.save(category);
    }
}
