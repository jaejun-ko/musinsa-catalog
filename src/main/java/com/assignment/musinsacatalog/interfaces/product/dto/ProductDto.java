package com.assignment.musinsacatalog.interfaces.product.dto;

import com.assignment.musinsacatalog.domain.product.ProductCommand;
import lombok.Getter;
import lombok.Setter;

public class ProductDto {

    @Getter
    @Setter
    public static class RegisterRequest {

        private Long brandId;
        private Long categoryId;
        private Long price;

        public ProductCommand.RegisterProduct toCommand() {
            return ProductCommand.RegisterProduct.builder()
                    .brandId(brandId)
                    .categoryId(categoryId)
                    .price(price)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class ModifyRequest {

        private Long id;
        private Long brandId;
        private Long categoryId;
        private Long price;

        public ProductCommand.ModifyProduct toCommand() {
            return ProductCommand.ModifyProduct.builder()
                    .id(id)
                    .brandId(brandId)
                    .categoryId(categoryId)
                    .price(price)
                    .build();
        }
    }

    @Getter
    public static class Main {

        private final Long id;
        private final Long brandId;
        private final String brandName;
        private final Long categoryId;
        private final String categoryName;
        private final Long price;

        public Main(Long id, Long brandId, String brandName, Long categoryId, String categoryName, Long price) {
            this.id = id;
            this.brandId = brandId;
            this.brandName = brandName;
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.price = price;
        }
    }
}
