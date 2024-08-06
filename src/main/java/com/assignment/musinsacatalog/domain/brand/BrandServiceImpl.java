package com.assignment.musinsacatalog.domain.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandStore brandStore;
    private final BrandReader brandReader;

    @Transactional
    @Override
    public BrandInfo.Main registerBrand(BrandCommand.RegisterBrand command) {

        Brand brand = command.toEntity();

        Brand storedBrand = brandStore.store(brand);

        return new BrandInfo.Main(storedBrand);
    }

    @Transactional
    @Override
    public BrandInfo.Main modifyBrand(BrandCommand.ModifyBrand command) {

        Brand brand = brandReader.getBrand(command.getId());

        brand.changeName(command.getName());

        return new BrandInfo.Main(brand);
    }

    @Transactional
    @Override
    public void removeBrand(Long id) {

        Brand brand = brandReader.getBrand(id);

        brand.delete();
    }

    @Transactional(readOnly = true)
    @Override
    public BrandInfo.Main getBrand(Long id) {

        Brand brand = brandReader.getBrand(id);

        return new BrandInfo.Main(brand);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BrandInfo.Main> getBrands() {

        List<Brand> brands = brandReader.getBrands();

        return brands.stream()
                .map(BrandInfo.Main::new)
                .toList();
    }

    @Override
    public BrandInfo.Main getLowestPriceBrand() {

        Brand brand = brandReader.getLowestPriceBrandForAllCategories();

        return new BrandInfo.Main(brand);
    }
}
