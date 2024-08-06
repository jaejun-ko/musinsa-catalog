package com.assignment.musinsacatalog.application.category;

import com.assignment.musinsacatalog.domain.category.Category;
import com.assignment.musinsacatalog.domain.category.CategoryCommand;
import com.assignment.musinsacatalog.domain.category.CategoryInfo;
import com.assignment.musinsacatalog.domain.category.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryFacadeTests {

    @InjectMocks
    private CategoryFacade categoryFacade;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("카테고리 등록 테스트")
    public void testRegisterCategory() {

        // given
        String categoryName = "categoryName";
        CategoryCommand.RegisterCategory command = CategoryCommand.RegisterCategory.builder().name(categoryName).build();
        CategoryInfo.Main expectedCategory = new CategoryInfo.Main(command.toEntity());

        // when
        when(categoryService.registerCategory(any(CategoryCommand.RegisterCategory.class))).thenReturn(expectedCategory);

        CategoryInfo.Main actualCategory = categoryFacade.registerCategory(command);

        // then
        assertNotNull(actualCategory);
        assertEquals(expectedCategory, actualCategory);
        verify(categoryService, times(1)).registerCategory(command);
    }

    @Test
    @DisplayName("카테고리 수정 테스트")
    public void testModifyCategory() {

        // given
        String categoryName = "modifiedCategoryName";
        CategoryCommand.ModifyCategory command = CategoryCommand.ModifyCategory.builder()
                .name("modifiedCategoryName")
                .build();
        CategoryInfo.Main expectedCategory = new CategoryInfo.Main(Category.builder().name(categoryName).build());

        // when
        when(categoryService.modifyCategory(any(CategoryCommand.ModifyCategory.class))).thenReturn(expectedCategory);

        CategoryInfo.Main actualCategory = categoryFacade.modifyCategory(command);

        // then
        assertEquals(expectedCategory, actualCategory);
        verify(categoryService, times(1)).modifyCategory(command);
    }

    @Test
    @DisplayName("카테고리 삭제 테스트")
    public void testRemoveCategory() {

        // given
        Long categoryId = 1L;

        // when
        doNothing().when(categoryService).removeCategory(categoryId);

        categoryFacade.removeCategory(categoryId);

        // then
        verify(categoryService, times(1)).removeCategory(categoryId);
    }

    @Test
    @DisplayName("카테고리 조회 테스트")
    public void testGetCategory() {

        // given
        Long categoryId = 1L;
        String categoryName = "categoryName";
        CategoryInfo.Main expectedCategory = new CategoryInfo.Main(Category.builder().name(categoryName).build());

        // when
        when(categoryService.getCategory(categoryId)).thenReturn(expectedCategory);

        CategoryInfo.Main actualCategory = categoryFacade.getCategory(categoryId);

        // then
        assertNotNull(actualCategory);
        assertEquals(expectedCategory, actualCategory);
        verify(categoryService, times(1)).getCategory(categoryId);
    }

    @Test
    @DisplayName("카테고리 목록 조회 테스트")
    public void testGetCategorys() {

        // given
        Category category1 = Category.builder().name("category1").build();
        Category category2 = Category.builder().name("category2").build();
        List<CategoryInfo.Main> expectedCategories = Arrays.asList(new CategoryInfo.Main(category1), new CategoryInfo.Main(category2));

        // when
        when(categoryService.getCategories()).thenReturn(expectedCategories);

        List<CategoryInfo.Main> actualCategories = categoryFacade.getCategories();

        // then
        assertEquals(expectedCategories.size(), actualCategories.size());
        assertEquals(expectedCategories, actualCategories);
        verify(categoryService, times(1)).getCategories();
    }
}
