package com.assignment.musinsacatalog.interfaces.brand.dto;

import com.assignment.musinsacatalog.domain.brand.BrandInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface BrandDtoMapper {

    BrandDto.Main of(BrandInfo.Main mainResult);
}
