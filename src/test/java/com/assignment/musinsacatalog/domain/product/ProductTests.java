package com.assignment.musinsacatalog.domain.product;

import com.assignment.musinsacatalog.common.exception.InvalidParamException;
import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.category.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductTests {

    @Test
    @DisplayName("상품 생성 테스트")
    void testProductCreation() {
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().brand(brand).category(category).price(100L).build();
        assertEquals(1L, product.getBrand().getId());
        assertEquals(2L, product.getCategory().getId());
        assertEquals(100L, product.getPrice());
        assertFalse(product.getDeleted());
    }

    @Test
    @DisplayName("상품 생성 시 유효하지 않은 파라미터로 생성 시도")
    void testProductCreationWithInvalidParams() {

        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        assertThrows(InvalidParamException.class, () -> Product.builder().brand(null).category(category).price(100L).build());
        assertThrows(InvalidParamException.class, () -> Product.builder().brand(brand).category(null).price(100L).build());
        assertThrows(InvalidParamException.class, () -> Product.builder().brand(brand).category(category).price(-100L).build());
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void testModifyProduct() {

        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().brand(brand).category(category).price(100L).build();
        product.modify(brand, category, 200L);
        assertEquals(3L, product.getBrand().getId());
        assertEquals(4L, product.getCategory().getId());
        assertEquals(200L, product.getPrice());
    }

    @Test
    @DisplayName("상품 수정 시 유효하지 않은 파라미터로 수정 시도")
    void testModifyProductWithInvalidParams() {

        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();

        Product product = Product.builder().brand(brand).category(category).price(100L).build();
        assertThrows(InvalidParamException.class, () -> product.modify(brand, null, 200L));
        assertThrows(InvalidParamException.class, () -> product.modify(null, category, 200L));
        assertThrows(InvalidParamException.class, () -> product.modify(brand, category, -200L));
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void testDeleteProduct() {
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().brand(brand).category(category).price(100L).build();
        product.delete();
        assertTrue(product.getDeleted());
    }
}
