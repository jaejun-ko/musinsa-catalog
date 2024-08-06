package com.assignment.musinsacatalog.interfaces.category.dto;

import com.assignment.musinsacatalog.domain.category.CategoryCommand;
import lombok.Getter;
import lombok.Setter;

public class CategoryDto {

    @Getter
    @Setter
    public static class RegisterRequest {

        private String name;

        public CategoryCommand.RegisterCategory toCommand() {
            return CategoryCommand.RegisterCategory.builder()
                    .name(name)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class ModifyRequest {

        private Long id;
        private String name;

        public CategoryCommand.ModifyCategory toCommand() {
            return CategoryCommand.ModifyCategory.builder()
                    .id(id)
                    .name(name)
                    .build();
        }
    }

    @Getter
    public static class Main {

        private final Long id;
        private final String name;

        public Main(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
