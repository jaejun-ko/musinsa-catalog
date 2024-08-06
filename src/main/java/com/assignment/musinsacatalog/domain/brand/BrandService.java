package com.assignment.musinsacatalog.domain.brand;

import java.util.List;

public interface BrandService {

    BrandInfo.Main registerBrand(BrandCommand.RegisterBrand command);

    BrandInfo.Main modifyBrand(BrandCommand.ModifyBrand command);

    void removeBrand(Long id);

    BrandInfo.Main getBrand(Long id);

    List<BrandInfo.Main> getBrands();

    BrandInfo.Main getLowestPriceBrand();
}
