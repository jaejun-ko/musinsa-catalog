package com.assignment.musinsacatalog.domain.recommendation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class RecommendationInfo {

    @Getter
    @Builder
    public static class LowestPriceProductOfEachCategory {

        private final List<CategoryProduct> categoryProducts;
        private final Long totalPrice;
    }

    @Getter
    @Builder
    public static class CategoryProduct {

        private final String categoryName;
        private final String brandName;
        private final Long price;
    }

    @Getter
    @Builder
    public static class LowestPriceBrand {

        private final String brandName;
        private final List<LowestPriceCategory> lowestPriceCategories;
        private final Long totalPrice;
    }

    @Getter
    @Builder
    public static class LowestPriceCategory {

        private final String categoryName;
        private final Long price;
    }

    @Getter
    @Builder
    public static class LowestPriceProductByCategory {

        private final String categoryName;
        private final List<BrandPrice> lowest;
        private final List<BrandPrice> highest;
    }

    @Getter
    @Builder
    public static class BrandPrice {

        private final String brandName;
        private final Long price;
    }
}
