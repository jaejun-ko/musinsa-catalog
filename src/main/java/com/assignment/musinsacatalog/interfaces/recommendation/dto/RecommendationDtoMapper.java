package com.assignment.musinsacatalog.interfaces.recommendation.dto;

import com.assignment.musinsacatalog.domain.recommendation.RecommendationInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface RecommendationDtoMapper {

    RecommendationDto.LowestPriceProductOfEachCategory of(RecommendationInfo.LowestPriceProductOfEachCategory lowestPriceProductOfEachCategory);
    RecommendationDto.LowestPriceBrand of(RecommendationInfo.LowestPriceBrand lowestPriceBrand);
    RecommendationDto.LowestPriceProductByCategory of(RecommendationInfo.LowestPriceProductByCategory lowestPriceProductByCategory);
}
