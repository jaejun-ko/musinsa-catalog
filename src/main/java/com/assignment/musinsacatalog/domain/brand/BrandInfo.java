package com.assignment.musinsacatalog.domain.brand;

import lombok.EqualsAndHashCode;
import lombok.Getter;

public class BrandInfo {

    @Getter
    @EqualsAndHashCode
    public static class Main {

        private final Long id;
        private final String name;

        public Main(Brand brand) {
            this.id = brand.getId();
            this.name = brand.getName();
        }
    }
}
