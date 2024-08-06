package com.assignment.musinsacatalog.infrastructure.product;

import com.assignment.musinsacatalog.domain.product.Product;
import com.assignment.musinsacatalog.domain.product.ProductStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductStoreImpl implements ProductStore {

    private final ProductRepository productRepository;

    @Override
    public Product store(Product product) {

        return productRepository.save(product);
    }
}
