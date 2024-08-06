package com.assignment.musinsacatalog.domain.brand;

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

public class BrandServiceTests {

    @InjectMocks
    private BrandServiceImpl brandService;

    @Mock
    private BrandStore brandStore;

    @Mock
    private BrandReader brandReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("브랜드 등록 테스트")
    void testRegisterBrand() {
        BrandCommand.RegisterBrand command = new BrandCommand.RegisterBrand("Brand A");
        Brand brand = Brand.builder().name("Brand A").build();
        when(brandStore.store(ArgumentMatchers.any(Brand.class))).thenReturn(brand);

        BrandInfo.Main result = brandService.registerBrand(command);

        assertEquals("Brand A", result.getName());
        verify(brandStore, times(1)).store(ArgumentMatchers.any(Brand.class));
    }

    @Test
    @DisplayName("브랜드 수정 테스트")
    void testModifyBrand() {
        BrandCommand.ModifyBrand command = new BrandCommand.ModifyBrand(1L, "Brand B");
        Brand brand = Brand.builder().name("Brand A").build();
        when(brandReader.getBrand(1L)).thenReturn(brand);

        BrandInfo.Main result = brandService.modifyBrand(command);

        assertEquals("Brand B", result.getName());
        verify(brandReader, times(1)).getBrand(1L);
    }

    @Test
    @DisplayName("브랜드 삭제 테스트")
    void testRemoveBrand() {
        Brand brand = Brand.builder().name("Brand A").build();
        when(brandReader.getBrand(1L)).thenReturn(brand);

        brandService.removeBrand(1L);

        assertTrue(brand.getDeleted());
        verify(brandReader, times(1)).getBrand(1L);
    }

    @Test
    @DisplayName("브랜드 조회 테스트")
    void testGetBrand() {
        Brand brand = Brand.builder().name("Brand A").build();
        when(brandReader.getBrand(1L)).thenReturn(brand);

        BrandInfo.Main result = brandService.getBrand(1L);

        assertEquals("Brand A", result.getName());
        verify(brandReader, times(1)).getBrand(1L);
    }

    @Test
    void testGetBrands() {
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        List<Brand> brands = Arrays.asList(brand1, brand2);
        when(brandReader.getBrands()).thenReturn(brands);

        List<BrandInfo.Main> result = brandService.getBrands();

        assertEquals(2, result.size());
        verify(brandReader, times(1)).getBrands();
    }

    @Test
    @DisplayName("가장 저렴한 가격의 브랜드 조회 테스트")
    void testGetLowestPriceBrand() {
        Brand brand = Brand.builder().name("Brand A").build();
        when(brandReader.getLowestPriceBrandForAllCategories()).thenReturn(brand);

        BrandInfo.Main result = brandService.getLowestPriceBrand();

        assertEquals("Brand A", result.getName());
        verify(brandReader, times(1)).getLowestPriceBrandForAllCategories();
    }
}
