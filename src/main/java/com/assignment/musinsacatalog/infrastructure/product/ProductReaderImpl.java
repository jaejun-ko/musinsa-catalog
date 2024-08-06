package com.assignment.musinsacatalog.infrastructure.product;

import com.assignment.musinsacatalog.common.exception.EntityNotFoundException;
import com.assignment.musinsacatalog.domain.product.Product;
import com.assignment.musinsacatalog.domain.product.ProductReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductReaderImpl implements ProductReader {

    private final ProductRepository productRepository;
    private final ProductQueryDslRepository productQueryDslRepository;

    @Override
    public Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Product> getProducts() {

        return productRepository.findAllProducts();
    }

    @Override
    public List<Product> getLowestPriceProductsOfEachCategory() {

        return productQueryDslRepository.findLowestPriceProductsOfEachCategory();
    }

    @Override
    public List<Product> getLowestTotalPriceProductsByBrandId(Long brandId) {

        return productQueryDslRepository.findLowestTotalPriceProductsByBrandId(brandId);
    }

    @Override
    public List<Product> getLowestPriceProductByCategoryId(Long categoryId) {

        return productQueryDslRepository.findLowestPriceProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> getHighestPriceProductByCategoryId(Long categoryId) {

        return productQueryDslRepository.findHighestPriceProductsByCategoryId(categoryId);
    }
}
