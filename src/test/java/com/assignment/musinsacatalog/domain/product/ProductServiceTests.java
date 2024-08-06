package com.assignment.musinsacatalog.domain.product;

import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.brand.BrandReader;
import com.assignment.musinsacatalog.domain.category.Category;
import com.assignment.musinsacatalog.domain.category.CategoryReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTests {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductStore productStore;

    @Mock
    private ProductReader productReader;

    @Mock
    private BrandReader brandReader;

    @Mock
    private CategoryReader categoryReader;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("상품 등록 테스트")
    void testRegisterProduct() {

        // given
        ProductCommand.RegisterProduct command = new ProductCommand.RegisterProduct(1L, 2L, 100L);
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().brand(brand).category(category).price(100L).build();

        // when
        when(brandReader.getBrand(any())).thenReturn(brand);
        when(categoryReader.getCategory(any())).thenReturn(category);
        when(productStore.store(any(Product.class))).thenReturn(product);

        ProductInfo.Main result = productService.registerProduct(command);

        // then
        assertEquals("Brand A", result.getBrandName());
        assertEquals("Category A", result.getCategoryName());
        assertEquals(100L, result.getPrice());
        verify(productStore, times(1)).store(any(Product.class));
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void testModifyProduct() {

        // given
        ProductCommand.ModifyProduct command = new ProductCommand.ModifyProduct(1L, 3L, 4L, 200L);
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().brand(brand).category(category).price(100L).build();

        // when
        when(brandReader.getBrand(any())).thenReturn(brand);
        when(categoryReader.getCategory(any())).thenReturn(category);
        when(productReader.getProduct(1L)).thenReturn(product);

        ProductInfo.Main result = productService.modifyProduct(command);

        // then
        assertEquals("Brand A", result.getBrandName());
        assertEquals("Category A", result.getCategoryName());
        assertEquals(200L, result.getPrice());
        verify(productReader, times(1)).getProduct(1L);
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void testRemoveProduct() {

        // given
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().brand(brand).category(category).price(100L).build();

        // when
        when(productReader.getProduct(1L)).thenReturn(product);

        productService.removeProduct(1L);

        // then
        assertTrue(product.getDeleted());
        verify(productReader, times(1)).getProduct(1L);
    }

    @Test
    @DisplayName("상품 조회 테스트")
    void testGetProduct() {

        // given
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().brand(brand).category(category).price(100L).build();

        // when
        when(productReader.getProduct(1L)).thenReturn(product);

        ProductInfo.Main result = productService.getProduct(1L);

        // then
        assertEquals("Brand A", result.getBrandName());
        assertEquals("Category A", result.getCategoryName());
        assertEquals(100L, result.getPrice());
        verify(productReader, times(1)).getProduct(1L);
    }

    @Test
    @DisplayName("상품 목록 조회 테스트")
    void testGetProducts() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Category category1 = Category.builder().name("Category A").build();
        Product product1 = Product.builder().brand(brand1).category(category1).price(100L).build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Category category2 = Category.builder().name("Category B").build();
        Product product2 = Product.builder().brand(brand2).category(category2).price(200L).build();
        List<Product> products = Arrays.asList(product1, product2);

        // when
        when(productReader.getProducts()).thenReturn(products);

        List<ProductInfo.Main> result = productService.getProducts();

        // then
        assertEquals(2, result.size());
        verify(productReader, times(1)).getProducts();
    }

    @Test
    @DisplayName("카테고리별 최저가 상품 목록 조회 테스트")
    void testGetLowestPriceProductsOfEachCategory() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Category category1 = Category.builder().name("Category A").build();
        Category category2 = Category.builder().name("Category B").build();
        Category category3 = Category.builder().name("Category C").build();
        Category category4 = Category.builder().name("Category D").build();
        Category category5 = Category.builder().name("Category E").build();

        Product product1 = Product.builder().brand(brand1).category(category1).price(100L).build();
        Product product2 = Product.builder().brand(brand2).category(category2).price(100L).build();
        Product product3 = Product.builder().brand(brand1).category(category3).price(100L).build();
        Product product4 = Product.builder().brand(brand2).category(category4).price(100L).build();
        Product product5 = Product.builder().brand(brand1).category(category5).price(100L).build();
        List<Product> products = Arrays.asList(product1, product2, product3, product4, product5);

        // when
        when(productReader.getLowestPriceProductsOfEachCategory()).thenReturn(products);

        List<ProductInfo.Main> result = productService.getLowestPriceProductsOfEachCategory();

        // then
        assertEquals(5, result.size());
        assertEquals("Category A", result.get(0).getCategoryName());
        assertEquals(100L, result.get(0).getPrice());
        assertEquals("Category E", result.get(4).getCategoryName());
        assertEquals(100L, result.get(4).getPrice());
        verify(productReader, times(1)).getLowestPriceProductsOfEachCategory();
    }

    @Test
    @DisplayName("브랜드별 최저가 상품 목록 조회 테스트")
    void testGetLowestTotalPriceProductsByBrandId() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Brand brand3 = Brand.builder().name("Brand C").build();
        Category category1 = Category.builder().name("Category A").build();
        Category category2 = Category.builder().name("Category B").build();
        Product product1 = Product.builder().brand(brand1).category(category1).price(100L).build();
        Product product2 = Product.builder().brand(brand1).category(category2).price(200L).build();
        Product product3 = Product.builder().brand(brand2).category(category1).price(100L).build();
        Product product4 = Product.builder().brand(brand2).category(category2).price(200L).build();
        Product product5 = Product.builder().brand(brand3).category(category2).price(200L).build();
        List<Product> products = Arrays.asList(product1, product2, product3, product4, product5);

        // when
        when(productReader.getLowestTotalPriceProductsByBrandId(1L)).thenReturn(List.of(products.get(0), products.get(1)));

        List<ProductInfo.Main> result = productService.getLowestTotalPriceProductsByBrandId(1L);

        // then
        assertEquals(2, result.size());
        assertEquals("Category A", result.get(0).getCategoryName());
        assertEquals(100L, result.get(0).getPrice());
        assertEquals("Category B", result.get(1).getCategoryName());
        assertEquals(200L, result.get(1).getPrice());
        verify(productReader, times(1)).getLowestTotalPriceProductsByBrandId(1L);
    }

    @Test
    @DisplayName("카테고리별 최저가 상품 목록 조회 테스트")
    void testGetLowestPriceProductByCategoryId() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Brand brand3 = Brand.builder().name("Brand C").build();
        Category category1 = Category.builder().name("Category A").build();

        Product product1 = Product.builder().brand(brand1).category(category1).price(100L).build();
        Product product2 = Product.builder().brand(brand2).category(category1).price(200L).build();
        Product product3 = Product.builder().brand(brand3).category(category1).price(300L).build();
        List<Product> products = Arrays.asList(product1, product2, product3);

        // when
        when(productReader.getLowestPriceProductByCategoryId(2L)).thenReturn(List.of(products.get(0)));

        List<ProductInfo.Main> result = productService.getLowestPriceProductByCategoryId(2L);

        // then
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).getPrice());
        verify(productReader, times(1)).getLowestPriceProductByCategoryId(2L);
    }

    @Test
    @DisplayName("카테고리별 최고가 상품 목록 조회 테스트")
    void testGetHighestPriceProductByCategoryId() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Brand brand3 = Brand.builder().name("Brand C").build();
        Category category1 = Category.builder().name("Category A").build();
        Product product1 = Product.builder().brand(brand1).category(category1).price(100L).build();
        Product product2 = Product.builder().brand(brand2).category(category1).price(200L).build();
        Product product3 = Product.builder().brand(brand3).category(category1).price(300L).build();
        List<Product> products = Arrays.asList(product1, product2, product3);

        // when
        when(productReader.getHighestPriceProductByCategoryId(2L)).thenReturn(List.of(products.get(2)));

        List<ProductInfo.Main> result = productService.getHighestPriceProductByCategoryId(2L);

        // then
        assertEquals(1, result.size());
        assertEquals(300L, result.get(0).getPrice());
        verify(productReader, times(1)).getHighestPriceProductByCategoryId(2L);
    }
}
