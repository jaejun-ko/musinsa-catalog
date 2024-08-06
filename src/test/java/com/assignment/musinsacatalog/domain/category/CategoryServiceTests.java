package com.assignment.musinsacatalog.domain.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceTests {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryStore categoryStore;

    @Mock
    private CategoryReader categoryReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("카테고리 등록 테스트")
    void testRegisterCategory() {
        CategoryCommand.RegisterCategory command = new CategoryCommand.RegisterCategory("Category A");
        Category category = Category.builder().name("Category A").build();
        when(categoryStore.store(ArgumentMatchers.any(Category.class))).thenReturn(category);

        CategoryInfo.Main result = categoryService.registerCategory(command);

        assertEquals("Category A", result.getName());
        verify(categoryStore, times(1)).store(ArgumentMatchers.any(Category.class));
    }

    @Test
    @DisplayName("카테고리 수정 테스트")
    void testModifyCategory() {
        CategoryCommand.ModifyCategory command = new CategoryCommand.ModifyCategory(1L, "Category B");
        Category category = Category.builder().name("Category A").build();
        when(categoryReader.getCategory(1L)).thenReturn(category);

        CategoryInfo.Main result = categoryService.modifyCategory(command);

        assertEquals("Category B", result.getName());
        verify(categoryReader, times(1)).getCategory(1L);
    }

    @Test
    @DisplayName("카테고리 삭제 테스트")
    void testRemoveCategory() {
        Category category = Category.builder().name("Category A").build();
        when(categoryReader.getCategory(1L)).thenReturn(category);

        categoryService.removeCategory(1L);

        assertTrue(category.getDeleted());
        verify(categoryReader, times(1)).getCategory(1L);
    }

    @Test
    @DisplayName("카테고리 조회 테스트")
    void testGetCategory() {
        Category category = Category.builder().name("Category A").build();
        when(categoryReader.getCategory(1L)).thenReturn(category);

        CategoryInfo.Main result = categoryService.getCategory(1L);

        assertEquals("Category A", result.getName());
        verify(categoryReader, times(1)).getCategory(1L);
    }

    @Test
    @DisplayName("카테고리 목록 조회 테스트")
    void testGetCategories() {
        Category category1 = Category.builder().name("Category A").build();
        Category category2 = Category.builder().name("Category B").build();
        List<Category> categories = Arrays.asList(category1, category2);
        when(categoryReader.getCategories()).thenReturn(categories);

        List<CategoryInfo.Main> result = categoryService.getCategories();

        assertEquals(2, result.size());
        verify(categoryReader, times(1)).getCategories();
    }

    @Test
    @DisplayName("카테고리 이름으로 조회 테스트")
    void testGetCategoryByName() {
        Category category = Category.builder().name("Category A").build();
        when(categoryReader.getCategoryByName("Category A")).thenReturn(category);

        CategoryInfo.Main result = categoryService.getCategoryByName("Category A");

        assertEquals("Category A", result.getName());
        verify(categoryReader, times(1)).getCategoryByName("Category A");
    }
}
