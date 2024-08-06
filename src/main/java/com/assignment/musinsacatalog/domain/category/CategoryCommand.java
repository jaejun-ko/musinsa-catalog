package com.assignment.musinsacatalog.domain.category;

import lombok.Builder;
import lombok.Getter;

public class CategoryCommand {

    @Getter
    @Builder
    public static class RegisterCategory {

        private final String name;

        public Category toEntity() {
            return Category.builder()
                    .name(name)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class ModifyCategory {

        private final Long id;
        private final String name;
    }
}
