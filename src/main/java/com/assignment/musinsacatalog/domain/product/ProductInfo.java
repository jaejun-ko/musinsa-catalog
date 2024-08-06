package com.assignment.musinsacatalog.domain.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;

public class ProductInfo {

    @Getter
    @EqualsAndHashCode
    public static class Main {

        private final Long id;
        private final Long brandId;
        private final String brandName;
        private final Long categoryId;
        private final String categoryName;
        private final Long price;

        public Main(Product product) {
            this.id = product.getId();
            this.brandId = product.getBrand().getId();
            this.brandName = product.getBrand().getName();
            this.categoryId = product.getCategory().getId();
            this.categoryName = product.getCategory().getName();
            this.price = product.getPrice();
        }
    }
}
