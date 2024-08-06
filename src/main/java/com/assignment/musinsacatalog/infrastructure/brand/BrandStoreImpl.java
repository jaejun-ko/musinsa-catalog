package com.assignment.musinsacatalog.infrastructure.brand;

import com.assignment.musinsacatalog.common.exception.InvalidParamException;
import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.brand.BrandStore;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandStoreImpl implements BrandStore {

    private final BrandRepository brandRepository;

    @Override
    public Brand store(Brand brand) {

        if (StringUtils.isBlank(brand.getName())) throw new InvalidParamException("brand.getName()");

        return brandRepository.save(brand);
    }
}
