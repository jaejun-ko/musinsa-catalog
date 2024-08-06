package com.assignment.musinsacatalog.application.brand;

import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.brand.BrandCommand;
import com.assignment.musinsacatalog.domain.brand.BrandInfo;
import com.assignment.musinsacatalog.domain.brand.BrandService;
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

public class BrandFacadeTests {

    @InjectMocks
    private BrandFacade brandFacade;

    @Mock
    private BrandService brandService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("브랜드 등록 테스트")
    public void testRegisterBrand() {

        // given
        String brandName = "brandName";
        BrandCommand.RegisterBrand command = BrandCommand.RegisterBrand.builder().name(brandName).build();
        BrandInfo.Main expectedBrand = new BrandInfo.Main(command.toEntity());

        // when
        when(brandService.registerBrand(any(BrandCommand.RegisterBrand.class))).thenReturn(expectedBrand);

        BrandInfo.Main actualBrand = brandFacade.registerBrand(command);

        // then
        assertNotNull(actualBrand);
        assertEquals(expectedBrand, actualBrand);
        verify(brandService, times(1)).registerBrand(command);
    }

    @Test
    @DisplayName("브랜드 수정 테스트")
    public void testModifyBrand() {

        // given
        String brandName = "modifiedBrandName";
        BrandCommand.ModifyBrand command = BrandCommand.ModifyBrand.builder()
                .name("modifiedBrandName")
                .build();
        BrandInfo.Main expectedBrand = new BrandInfo.Main(Brand.builder().name(brandName).build());

        // when
        when(brandService.modifyBrand(any(BrandCommand.ModifyBrand.class))).thenReturn(expectedBrand);

        BrandInfo.Main actualBrand = brandFacade.modifyBrand(command);

        // then
        assertEquals(expectedBrand, actualBrand);
        verify(brandService, times(1)).modifyBrand(command);
    }

    @Test
    @DisplayName("브랜드 삭제 테스트")
    public void testRemoveBrand() {

        // given
        Long brandId = 1L;

        // when
        doNothing().when(brandService).removeBrand(brandId);

        brandFacade.removeBrand(brandId);

        // then
        verify(brandService, times(1)).removeBrand(brandId);
    }

    @Test
    @DisplayName("브랜드 조회 테스트")
    public void testGetBrand() {

        // given
        Long brandId = 1L;
        String brandName = "brandName";
        BrandInfo.Main expectedBrand = new BrandInfo.Main(Brand.builder().name(brandName).build());

        // when
        when(brandService.getBrand(brandId)).thenReturn(expectedBrand);

        BrandInfo.Main actualBrand = brandFacade.getBrand(brandId);

        // then
        assertNotNull(actualBrand);
        assertEquals(expectedBrand, actualBrand);
        verify(brandService, times(1)).getBrand(brandId);
    }

    @Test
    @DisplayName("브랜드 목록 조회 테스트")
    public void testGetBrands() {

        // given
        Brand brand1 = Brand.builder().name("brand1").build();
        Brand brand2 = Brand.builder().name("brand2").build();
        List<BrandInfo.Main> expectedBrands = Arrays.asList(new BrandInfo.Main(brand1), new BrandInfo.Main(brand2));

        // when
        when(brandService.getBrands()).thenReturn(expectedBrands);

        List<BrandInfo.Main> actualBrands = brandFacade.getBrands();

        // then
        assertEquals(expectedBrands.size(), actualBrands.size());
        assertEquals(expectedBrands, actualBrands);
        verify(brandService, times(1)).getBrands();
    }
}
