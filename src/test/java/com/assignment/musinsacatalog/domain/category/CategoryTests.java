package com.assignment.musinsacatalog.domain.category;

import com.assignment.musinsacatalog.common.exception.InvalidParamException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTests {

    @Test
    @DisplayName("카테고리 생성 테스트")
    void testCategoryCreation() {
        Category category = Category.builder().name("Category A").build();
        assertEquals("Category A", category.getName());
        assertFalse(category.getDeleted());
    }

    @Test
    @DisplayName("카테고리 생성 테스트 - 이름이 없는 경우")
    void testCategoryCreationWithInvalidName() {
        assertThrows(InvalidParamException.class, () -> {
            Category.builder().name("").build();
        });
    }

    @Test
    @DisplayName("카테고리 이름 변경 테스트")
    void testChangeCategoryName() {
        Category category = Category.builder().name("Category A").build();
        category.changeName("Category B");
        assertEquals("Category B", category.getName());
    }

    @Test
    @DisplayName("카테고리 이름 변경 테스트 - 이름이 없는 경우")
    void testChangeCategoryNameWithInvalidName() {
        Category category = Category.builder().name("Category A").build();
        assertThrows(InvalidParamException.class, () -> {
            category.changeName("");
        });
    }

    @Test
    @DisplayName("카테고리 삭제 테스트")
    void testDeleteCategory() {
        Category category = Category.builder().name("Category A").build();
        category.delete();
        assertTrue(category.getDeleted());
    }
}
