package com.assignment.musinsacatalog.application.brand;

import com.assignment.musinsacatalog.domain.brand.BrandCommand;
import com.assignment.musinsacatalog.domain.brand.BrandInfo;
import com.assignment.musinsacatalog.domain.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandFacade {

    private final BrandService brandService;

    public BrandInfo.Main registerBrand(BrandCommand.RegisterBrand command) {

        return brandService.registerBrand(command);
    }

    public BrandInfo.Main modifyBrand(BrandCommand.ModifyBrand command) {

        return brandService.modifyBrand(command);
    }

    public void removeBrand(Long id) {

        brandService.removeBrand(id);
    }

    public BrandInfo.Main getBrand(Long id) {

        return brandService.getBrand(id);
    }

    public List<BrandInfo.Main> getBrands() {

        return brandService.getBrands();
    }
}
