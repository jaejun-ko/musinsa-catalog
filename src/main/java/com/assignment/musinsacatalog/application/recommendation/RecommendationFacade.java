package com.assignment.musinsacatalog.application.recommendation;

import com.assignment.musinsacatalog.domain.brand.BrandInfo;
import com.assignment.musinsacatalog.domain.brand.BrandService;
import com.assignment.musinsacatalog.domain.category.CategoryInfo;
import com.assignment.musinsacatalog.domain.category.CategoryService;
import com.assignment.musinsacatalog.domain.product.ProductInfo;
import com.assignment.musinsacatalog.domain.product.ProductService;
import com.assignment.musinsacatalog.domain.recommendation.RecommendationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationFacade {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public RecommendationInfo.LowestPriceProductOfEachCategory getLowestPriceProductsOfEachCategory() {

        List<ProductInfo.Main> products = productService.getLowestPriceProductsOfEachCategory();

        Long totalPrice = products.stream().map(ProductInfo.Main::getPrice).reduce(0L, Long::sum);
        List<RecommendationInfo.CategoryProduct> categoryProducts = products.stream()
                .map(product -> RecommendationInfo.CategoryProduct.builder()
                        .categoryName(product.getCategoryName())
                        .brandName(product.getBrandName())
                        .price(product.getPrice())
                        .build())
                .toList();

        return RecommendationInfo.LowestPriceProductOfEachCategory.builder()
                .categoryProducts(categoryProducts)
                .totalPrice(totalPrice)
                .build();
    }

    public RecommendationInfo.LowestPriceBrand getLowestPriceBrandForAllCategories() {

        BrandInfo.Main brand = brandService.getLowestPriceBrand();
        List<ProductInfo.Main> products = productService.getLowestTotalPriceProductsByBrandId(brand.getId());

        Long totalPrice = products.stream().map(ProductInfo.Main::getPrice).reduce(0L, Long::sum);

        List<RecommendationInfo.LowestPriceCategory> lowestPriceCategories = products.stream()
                .map(product -> RecommendationInfo.LowestPriceCategory.builder()
                        .categoryName(product.getCategoryName())
                        .price(product.getPrice())
                        .build())
                .toList();

        return RecommendationInfo.LowestPriceBrand.builder()
                .brandName(brand.getName())
                .lowestPriceCategories(lowestPriceCategories)
                .totalPrice(totalPrice)
                .build();
    }

    public RecommendationInfo.LowestPriceProductByCategory getLowestPriceProductByCategory(String categoryName) {

        CategoryInfo.Main category = categoryService.getCategoryByName(categoryName);
        List<ProductInfo.Main> lowest = productService.getLowestPriceProductByCategoryId(category.getId());
        List<ProductInfo.Main> highest = productService.getHighestPriceProductByCategoryId(category.getId());

        List<RecommendationInfo.BrandPrice> lowestBrandPrices = lowest.stream()
                .map(product -> RecommendationInfo.BrandPrice.builder()
                        .brandName(product.getBrandName())
                        .price(product.getPrice())
                        .build())
                .toList();

        List<RecommendationInfo.BrandPrice> highestBrandPrices = highest.stream()
                .map(product -> RecommendationInfo.BrandPrice.builder()
                        .brandName(product.getBrandName())
                        .price(product.getPrice())
                        .build())
                .toList();

        return RecommendationInfo.LowestPriceProductByCategory.builder()
                .categoryName(category.getName())
                .lowest(lowestBrandPrices)
                .highest(highestBrandPrices)
                .build();
    }
}
