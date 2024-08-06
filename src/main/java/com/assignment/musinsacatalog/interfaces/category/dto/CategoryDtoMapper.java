package com.assignment.musinsacatalog.interfaces.category.dto;


import com.assignment.musinsacatalog.domain.category.CategoryInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface CategoryDtoMapper {

    CategoryDto.Main of(CategoryInfo.Main mainResult);
}
