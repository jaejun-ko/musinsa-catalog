package com.assignment.musinsacatalog.interfaces.brand.dto;

import com.assignment.musinsacatalog.domain.brand.BrandCommand;
import lombok.Getter;
import lombok.Setter;

public class BrandDto {

    @Getter
    @Setter
    public static class RegisterRequest {

        private String name;

        public BrandCommand.RegisterBrand toCommand() {
            return BrandCommand.RegisterBrand.builder()
                    .name(name)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class ModifyRequest {

        private Long id;
        private String name;

        public BrandCommand.ModifyBrand toCommand() {
            return BrandCommand.ModifyBrand.builder()
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
