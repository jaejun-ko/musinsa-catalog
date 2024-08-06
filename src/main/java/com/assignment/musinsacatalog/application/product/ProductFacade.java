package com.assignment.musinsacatalog.application.product;

import com.assignment.musinsacatalog.domain.product.ProductCommand;
import com.assignment.musinsacatalog.domain.product.ProductInfo;
import com.assignment.musinsacatalog.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;

    public ProductInfo.Main registerProduct(ProductCommand.RegisterProduct command) {

        return productService.registerProduct(command);
    }

    public ProductInfo.Main modifyProduct(ProductCommand.ModifyProduct command) {

        return productService.modifyProduct(command);
    }

    public void removeProduct(Long id) {

        productService.removeProduct(id);
    }

    public ProductInfo.Main getProduct(Long id) {

        return productService.getProduct(id);
    }

    public List<ProductInfo.Main> getProducts() {

        return productService.getProducts();
    }
}
