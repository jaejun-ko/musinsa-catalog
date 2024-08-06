package com.assignment.musinsacatalog.domain.brand;

import lombok.Builder;
import lombok.Getter;

public class BrandCommand {

    @Getter
    @Builder
    public static class RegisterBrand {

        private final String name;

        public Brand toEntity() {
            return Brand.builder()
                    .name(name)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ModifyBrand {

        private final Long id;
        private final String name;
    }
}
