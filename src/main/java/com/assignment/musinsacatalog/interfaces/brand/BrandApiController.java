package com.assignment.musinsacatalog.interfaces.brand;

import com.assignment.musinsacatalog.application.brand.BrandFacade;
import com.assignment.musinsacatalog.common.response.CommonResponse;
import com.assignment.musinsacatalog.interfaces.brand.dto.BrandDto;
import com.assignment.musinsacatalog.interfaces.brand.dto.BrandDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandApiController {

    private final BrandFacade brandFacade;

    private final BrandDtoMapper brandDtoMapper;

    @PostMapping
    public CommonResponse registerBrand(@RequestBody BrandDto.RegisterRequest request) {

        var command = request.toCommand();
        var brandInfo = brandFacade.registerBrand(command);
        var response = brandDtoMapper.of(brandInfo);

        return CommonResponse.success(response);
    }

    @PutMapping
    public CommonResponse modifyBrand(@RequestBody BrandDto.ModifyRequest request) {

        var command = request.toCommand();
        var brandInfo = brandFacade.modifyBrand(command);
        var response = brandDtoMapper.of(brandInfo);

        return CommonResponse.success(response);
    }

    @DeleteMapping("/{id}")
    public CommonResponse removeBrand(@PathVariable Long id) {

        brandFacade.removeBrand(id);

        return CommonResponse.success();
    }

    @GetMapping("/{id}")
    public CommonResponse getBrand(@PathVariable Long id) {

        var brandInfo = brandFacade.getBrand(id);
        var response = brandDtoMapper.of(brandInfo);

        return CommonResponse.success(response);
    }

    @GetMapping
    public CommonResponse getBrands() {

        var brandInfos = brandFacade.getBrands();
        var response = brandInfos.stream().map(brandDtoMapper::of).toList();

        return CommonResponse.success(response);
    }
}
