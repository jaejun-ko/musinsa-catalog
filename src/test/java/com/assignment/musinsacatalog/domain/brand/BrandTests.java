package com.assignment.musinsacatalog.domain.brand;

import com.assignment.musinsacatalog.common.exception.InvalidParamException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrandTests {

    @Test
    @DisplayName("브랜드 생성 테스트")
    void testBrandCreation() {

        // given
        Brand brand = Brand.builder().name("Brand A").build();

        // then
        assertEquals("Brand A", brand.getName());
        assertFalse(brand.getDeleted());
    }

    @Test
    @DisplayName("브랜드 생성 테스트 - 이름이 없는 경우")
    void testBrandCreationWithInvalidName() {

        // given & then
        assertThrows(InvalidParamException.class, () -> Brand.builder().name("").build());
    }

    @Test
    @DisplayName("브랜드 이름 변경 테스트")
    void testChangeBrandName() {

        // given
        Brand brand = Brand.builder().name("Brand A").build();
        brand.changeName("Brand B");

        // then
        assertEquals("Brand B", brand.getName());
    }

    @Test
    @DisplayName("브랜드 이름 변경 테스트 - 이름이 없는 경우")
    void testChangeBrandNameWithInvalidName() {

        // given
        Brand brand = Brand.builder().name("Brand A").build();

        // then
        assertThrows(InvalidParamException.class, () -> brand.changeName(""));
    }

    @Test
    @DisplayName("브랜드 삭제 테스트")
    void testDeleteBrand() {

        // given
        Brand brand = Brand.builder().name("Brand A").build();
        brand.delete();

        // then
        assertTrue(brand.getDeleted());
    }
}