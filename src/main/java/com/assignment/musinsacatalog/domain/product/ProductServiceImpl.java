package com.assignment.musinsacatalog.domain.product;

import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.brand.BrandReader;
import com.assignment.musinsacatalog.domain.category.Category;
import com.assignment.musinsacatalog.domain.category.CategoryReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductStore productStore;
    private final ProductReader productReader;

    private final BrandReader brandReader;
    private final CategoryReader categoryReader;

    @Transactional
    @Override
    public ProductInfo.Main registerProduct(ProductCommand.RegisterProduct command) {

        Brand brand = brandReader.getBrand(command.getBrandId());

        Category category = categoryReader.getCategory(command.getCategoryId());

        Product product = Product.builder()
                .brand(brand)
                .category(category)
                .price(command.getPrice())
                .build();

        Product storedProduct = productStore.store(product);

        return new ProductInfo.Main(storedProduct);
    }

    @Transactional
    @Override
    public ProductInfo.Main modifyProduct(ProductCommand.ModifyProduct command) {

        Product product = productReader.getProduct(command.getId());

        Brand brand = brandReader.getBrand(command.getBrandId());

        Category category = categoryReader.getCategory(command.getCategoryId());

        product.modify(brand, category, command.getPrice());

        return new ProductInfo.Main(product);
    }

    @Transactional
    @Override
    public void removeProduct(Long id) {

        Product product = productReader.getProduct(id);

        product.delete();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductInfo.Main getProduct(Long id) {

        Product product = productReader.getProduct(id);

        return new ProductInfo.Main(product);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductInfo.Main> getProducts() {

        List<Product> products = productReader.getProducts();

        return products.stream()
                .map(ProductInfo.Main::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductInfo.Main> getLowestPriceProductsOfEachCategory() {

        List<Product> products = productReader.getLowestPriceProductsOfEachCategory();

        return products.stream()
                .map(ProductInfo.Main::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductInfo.Main> getLowestTotalPriceProductsByBrandId(Long brandId) {

        List<Product> products = productReader.getLowestTotalPriceProductsByBrandId(brandId);

        return products.stream()
                .map(ProductInfo.Main::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProductInfo.Main> getLowestPriceProductByCategoryId(Long categoryId) {

        List<Product> products = productReader.getLowestPriceProductByCategoryId(categoryId);

        return products.stream()
                .map(ProductInfo.Main::new)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductInfo.Main> getHighestPriceProductByCategoryId(Long categoryId) {

        List<Product> products = productReader.getHighestPriceProductByCategoryId(categoryId);

        return products.stream()
                .map(ProductInfo.Main::new)
                .toList();
    }
}
