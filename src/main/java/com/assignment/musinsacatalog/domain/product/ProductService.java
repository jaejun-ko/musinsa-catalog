package com.assignment.musinsacatalog.domain.product;

import java.util.List;

public interface ProductService {

    ProductInfo.Main registerProduct(ProductCommand.RegisterProduct command);

    ProductInfo.Main modifyProduct(ProductCommand.ModifyProduct command);

    void removeProduct(Long id);

    ProductInfo.Main getProduct(Long id);

    List<ProductInfo.Main> getProducts();

    List<ProductInfo.Main> getLowestPriceProductsOfEachCategory();

    List<ProductInfo.Main> getLowestTotalPriceProductsByBrandId(Long brandId);

    List<ProductInfo.Main> getLowestPriceProductByCategoryId(Long categoryId);

    List<ProductInfo.Main> getHighestPriceProductByCategoryId(Long categoryId);
}
