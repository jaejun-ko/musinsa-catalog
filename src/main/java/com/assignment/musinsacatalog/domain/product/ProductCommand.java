package com.assignment.musinsacatalog.domain.product;

import lombok.Builder;
import lombok.Getter;

public class ProductCommand {

    @Getter
    @Builder
    public static class RegisterProduct {

        private final Long brandId;
        private final Long categoryId;
        private final Long price;
    }

    @Getter
    @Builder
    public static class ModifyProduct {

        private final Long id;
        private final Long brandId;
        private final Long categoryId;
        private final Long price;
    }
}
