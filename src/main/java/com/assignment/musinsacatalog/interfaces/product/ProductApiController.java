package com.assignment.musinsacatalog.interfaces.product;

import com.assignment.musinsacatalog.application.product.ProductFacade;
import com.assignment.musinsacatalog.common.response.CommonResponse;
import com.assignment.musinsacatalog.interfaces.product.dto.ProductDto;
import com.assignment.musinsacatalog.interfaces.product.dto.ProductDtoMapper;
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
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductFacade productFacade;
    private final ProductDtoMapper productDtoMapper;

    @PostMapping
    public CommonResponse registerProduct(@RequestBody ProductDto.RegisterRequest request) {

        var command = request.toCommand();
        var product = productFacade.registerProduct(command);
        var response = productDtoMapper.of(product);

        return CommonResponse.success(response);
    }

    @PutMapping
    public CommonResponse modifyProduct(@RequestBody ProductDto.ModifyRequest request) {

        var command = request.toCommand();
        var product = productFacade.modifyProduct(command);
        var response = productDtoMapper.of(product);

        return CommonResponse.success(response);
    }

    @DeleteMapping("/{id}")
    public CommonResponse removeProduct(@PathVariable Long id) {

        productFacade.removeProduct(id);

        return CommonResponse.success();
    }

    @GetMapping("/{id}")
    public CommonResponse getProduct(@PathVariable Long id) {

        var product = productFacade.getProduct(id);
        var response = productDtoMapper.of(product);

        return CommonResponse.success(response);
    }

    @GetMapping
    public CommonResponse getProducts() {

        var products = productFacade.getProducts();
        var response = products.stream().map(productDtoMapper::of).toList();

        return CommonResponse.success(response);
    }
}
