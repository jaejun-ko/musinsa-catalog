package com.assignment.musinsacatalog.interfaces.product.dto;

import com.assignment.musinsacatalog.domain.product.ProductInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProductDtoMapper {

    ProductDto.Main of(ProductInfo.Main mainResult);
}
