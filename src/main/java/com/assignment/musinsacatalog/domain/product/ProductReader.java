package com.assignment.musinsacatalog.domain.product;

import java.util.List;

public interface ProductReader {

    Product getProduct(Long id);

    List<Product> getProducts();

    List<Product> getLowestPriceProductsOfEachCategory();

    List<Product> getLowestTotalPriceProductsByBrandId(Long brandId);

    List<Product> getLowestPriceProductByCategoryId(Long categoryId);

    List<Product> getHighestPriceProductByCategoryId(Long categoryId);
}
