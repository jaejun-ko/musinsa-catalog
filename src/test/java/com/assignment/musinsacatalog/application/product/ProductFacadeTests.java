package com.assignment.musinsacatalog.application.product;

import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.category.Category;
import com.assignment.musinsacatalog.domain.product.Product;
import com.assignment.musinsacatalog.domain.product.ProductCommand;
import com.assignment.musinsacatalog.domain.product.ProductInfo;
import com.assignment.musinsacatalog.domain.product.ProductService;
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

public class ProductFacadeTests {

    @InjectMocks
    private ProductFacade productFacade;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("상품 등록 테스트")
    public void testRegisterProduct() {

        // given
        Long categoryId = 1L;
        Long brandId = 1L;
        Long price = 1000L;
        ProductCommand.RegisterProduct command = ProductCommand.RegisterProduct.builder()
                .categoryId(categoryId)
                .brandId(brandId)
                .price(price)
                .build();
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().category(category).brand(brand).price(price).build();
        ProductInfo.Main expectedProduct = new ProductInfo.Main(product);

        // when
        when(productService.registerProduct(any(ProductCommand.RegisterProduct.class))).thenReturn(expectedProduct);

        ProductInfo.Main actualProduct = productFacade.registerProduct(command);

        // then
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
        verify(productService, times(1)).registerProduct(command);
    }

    @Test
    @DisplayName("상품 수정 테스트")
    public void testModifyProduct() {

        // given
        Long categoryId = 2L;
        Long brandId = 2L;
        Long price = 2000L;
        ProductCommand.ModifyProduct command = ProductCommand.ModifyProduct.builder()
                .categoryId(categoryId)
                .brandId(brandId)
                .price(price)
                .build();
        Brand brand = Brand.builder().name("Brand B").build();
        Category category = Category.builder().name("Category B").build();
        Product product = Product.builder().category(category).brand(brand).price(price).build();
        ProductInfo.Main expectedProduct = new ProductInfo.Main(product);

        // when
        when(productService.modifyProduct(any(ProductCommand.ModifyProduct.class))).thenReturn(expectedProduct);

        ProductInfo.Main actualProduct = productFacade.modifyProduct(command);

        // then
        assertEquals(expectedProduct, actualProduct);
        verify(productService, times(1)).modifyProduct(command);
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    public void testRemoveProduct() {

        // given
        Long productId = 1L;

        // when
        doNothing().when(productService).removeProduct(productId);

        productFacade.removeProduct(productId);

        // then
        verify(productService, times(1)).removeProduct(productId);
    }

    @Test
    @DisplayName("상품 조회 테스트")
    public void testGetProduct() {

        // given
        Long productId = 1L;
        Long categoryId = 1L;
        Long brandId = 1L;
        Long price = 1000L;
        Brand brand = Brand.builder().name("Brand A").build();
        Category category = Category.builder().name("Category A").build();
        Product product = Product.builder().category(category).brand(brand).price(price).build();
        ProductInfo.Main expectedProduct = new ProductInfo.Main(product);

        // when
        when(productService.getProduct(productId)).thenReturn(expectedProduct);

        ProductInfo.Main actualProduct = productFacade.getProduct(productId);

        // then
        assertNotNull(actualProduct);
        assertEquals(expectedProduct, actualProduct);
        verify(productService, times(1)).getProduct(productId);
    }

    @Test
    @DisplayName("상품 목록 조회 테스트")
    public void testGetProducts() {

        // given
        Brand brand1 = Brand.builder().name("Brand A").build();
        Brand brand2 = Brand.builder().name("Brand B").build();
        Category category1 = Category.builder().name("Category A").build();
        Category category2 = Category.builder().name("Category B").build();
        Product product1 = Product.builder().category(category1).brand(brand1).price(1000L).build();
        Product product2 = Product.builder().category(category2).brand(brand2).price(2000L).build();
        List<ProductInfo.Main> expectedProducts = Arrays.asList(new ProductInfo.Main(product1), new ProductInfo.Main(product2));

        // when
        when(productService.getProducts()).thenReturn(expectedProducts);

        List<ProductInfo.Main> actualProducts = productFacade.getProducts();

        // then
        assertEquals(expectedProducts.size(), actualProducts.size());
        assertEquals(expectedProducts, actualProducts);
        verify(productService, times(1)).getProducts();
    }
}
