package com.assignment.musinsacatalog.interfaces.recommendation;

import com.assignment.musinsacatalog.application.recommendation.RecommendationFacade;
import com.assignment.musinsacatalog.common.response.CommonResponse;
import com.assignment.musinsacatalog.interfaces.recommendation.dto.RecommendationDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendations")
public class RecommendationApiController {

    private final RecommendationFacade recommendationFacade;
    private final RecommendationDtoMapper recommendationDtoMapper;

    @GetMapping("/lowest-price-products-of-each-category")
    public CommonResponse getLowestPriceProductsOfEachCategory() {

        var products = recommendationFacade.getLowestPriceProductsOfEachCategory();
        var response = recommendationDtoMapper.of(products);

        return CommonResponse.success(response);
    }

    @GetMapping("/lowest-price-brand")
    public CommonResponse getLowestPriceBrandForAllCategories() {

        var brand = recommendationFacade.getLowestPriceBrandForAllCategories();
        var response = recommendationDtoMapper.of(brand);

        return CommonResponse.success(response);
    }

    @GetMapping("/lowest-price-product-by-category")
    public CommonResponse getLowestPriceProductByCategory(@RequestParam String categoryName) {

        var products = recommendationFacade.getLowestPriceProductByCategory(categoryName);
        var response = recommendationDtoMapper.of(products);

        return CommonResponse.success(response);
    }
}
