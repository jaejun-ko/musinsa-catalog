package com.assignment.musinsacatalog.infrastructure.brand;

import com.assignment.musinsacatalog.common.exception.EntityNotFoundException;
import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.brand.BrandReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandReaderImpl implements BrandReader {

    private final BrandRepository brandRepository;
    private final BrandQueryDslRepository brandQueryDslRepository;

    @Override
    public Brand getBrand(Long id) {

        return brandRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Brand> getBrands() {

        return brandRepository.findAllBrands();
    }

    @Override
    public Brand getLowestPriceBrandForAllCategories() {

        return brandQueryDslRepository.findLowestPriceBrandForAllCategories()
                .orElseThrow(EntityNotFoundException::new);
    }
}
