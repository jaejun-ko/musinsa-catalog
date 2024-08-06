package com.assignment.musinsacatalog.domain.brand;

import java.util.List;

public interface BrandReader {

    Brand getBrand(Long id);

    List<Brand> getBrands();

    Brand getLowestPriceBrandForAllCategories();
}
