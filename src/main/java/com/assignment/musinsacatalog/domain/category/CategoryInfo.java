package com.assignment.musinsacatalog.domain.category;

import lombok.EqualsAndHashCode;
import lombok.Getter;

public class CategoryInfo {

    @Getter
    @EqualsAndHashCode
    public static class Main {

        private final Long id;
        private final String name;

        public Main(Category category) {
            this.id = category.getId();
            this.name = category.getName();
        }
    }
}
