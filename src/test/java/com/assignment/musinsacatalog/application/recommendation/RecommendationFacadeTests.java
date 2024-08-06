package com.assignment.musinsacatalog.application.recommendation;

import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.brand.BrandInfo;
import com.assignment.musinsacatalog.domain.brand.BrandService;
import com.assignment.musinsacatalog.domain.category.Category;
import com.assignment.musinsacatalog.domain.category.CategoryInfo;
import com.assignment.musinsacatalog.domain.category.CategoryService;
import com.assignment.musinsacatalog.domain.product.Product;
import com.assignment.musinsacatalog.domain.product.ProductInfo;
import com.assignment.musinsacatalog.domain.product.ProductService;
import com.assignment.musinsacatalog.domain.recommendation.RecommendationInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class RecommendationFacadeTests {

    @InjectMocks
    private RecommendationFacade recommendationFacade;

    @Mock
    private ProductService productService;

    @Mock
    private BrandService brandService;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLowestPriceProductsOfEachCategory() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Category category1 = Category.builder().name("Category A").build();
        Category category2 = Category.builder().name("Category B").build();
        Product product1 = new Product(brand1, category1, 100L);
        Product product2 = new Product(brand2, category2, 200L);
        List<ProductInfo.Main> mockProducts = Arrays.asList(
                new ProductInfo.Main(product1),
                new ProductInfo.Main(product2)
        );

        // when
        when(productService.getLowestPriceProductsOfEachCategory()).thenReturn(mockProducts);

        RecommendationInfo.LowestPriceProductOfEachCategory result = recommendationFacade.getLowestPriceProductsOfEachCategory();

        // then
        assertEquals(300L, result.getTotalPrice());
        assertEquals(2, result.getCategoryProducts().size());
        assertEquals(1L, result.getCategoryProducts().get(0).getCategoryId());
        assertEquals(100L, result.getCategoryProducts().get(0).getPrice());
    }

    @Test
    void testGetLowestPriceBrandForAllCategories() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Category category1 = Category.builder().name("Category A").build();
        Category category2 = Category.builder().name("Category B").build();
        Product product1 = Product.builder().brand(brand1).category(category1).price(100L).build();
        Product product2 = Product.builder().brand(brand2).category(category2).price(200L).build();
        BrandInfo.Main mockBrand = new BrandInfo.Main(brand1);
        List<ProductInfo.Main> mockProducts = Arrays.asList(
                new ProductInfo.Main(product1),
                new ProductInfo.Main(product2)
        );

        // when
        when(brandService.getLowestPriceBrand()).thenReturn(mockBrand);
        when(productService.getLowestTotalPriceProductsByBrandId(anyLong())).thenReturn(mockProducts);

        RecommendationInfo.LowestPriceBrand result = recommendationFacade.getLowestPriceBrandForAllCategories();

        // then
        assertEquals(1L, result.getBrandId());
        assertEquals(300L, result.getTotalPrice());
        assertEquals(2, result.getLowestPriceCategories().size());
    }

    @Test
    void testGetLowestPriceProductByCategory() {

        // given
        Category category1 = Category.builder().name("Category A").build();
        Category category2 = Category.builder().name("Category B").build();
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Product product1 = Product.builder().category(category1).brand(brand1).price(100L).build();
        Product product2 = Product.builder().category(category2).brand(brand2).price(200L).build();
        CategoryInfo.Main mockCategory = new CategoryInfo.Main(category1);
        List<ProductInfo.Main> mockLowestProducts = List.of(new ProductInfo.Main(product1));
        List<ProductInfo.Main> mockHighestProducts = List.of(new ProductInfo.Main(product2));

        // when
        when(categoryService.getCategoryByName(any(String.class))).thenReturn(mockCategory);
        when(productService.getLowestPriceProductByCategoryId(any())).thenReturn(mockLowestProducts);
        when(productService.getHighestPriceProductByCategoryId(any())).thenReturn(mockHighestProducts);

        RecommendationInfo.LowestPriceProductByCategory result = recommendationFacade.getLowestPriceProductByCategory("Category A");

        // then
        assertEquals(1, result.getLowest().size());
        assertEquals(1L, result.getLowest().get(0).getBrandId());
        assertEquals(100L, result.getLowest().get(0).getPrice());

        assertEquals(1, result.getHighest().size());
        assertEquals(2L, result.getHighest().get(0).getBrandId());
        assertEquals(200L, result.getHighest().get(0).getPrice());
    }
}
