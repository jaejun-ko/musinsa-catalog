package com.assignment.musinsacatalog.infrastructure.brand;

import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.brand.QBrand;
import com.assignment.musinsacatalog.domain.product.QProduct;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BrandQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public Optional<Brand> findLowestPriceBrandForAllCategories() {

        QProduct product = QProduct.product;
        QProduct subProduct = new QProduct("subProduct");
        QBrand brand = QBrand.brand;

        NumberPath<Long> categoryId = product.category.id;
        NumberPath<Long> brandId = product.brand.id;
        NumberPath<Long> price = product.price;

        Long cheapestBrandId = queryFactory
                .select(brandId)
                .from(product)
                .where(price.eq(
                        queryFactory
                                .select(subProduct.price.min())
                                .from(subProduct)
                                .where(
                                        subProduct.category.id.eq(categoryId),
                                        subProduct.brand.id.eq(brandId),
                                        subProduct.deleted.eq(false)
                                )
                        ),
                        product.deleted.eq(false)
                )
                .groupBy(brandId)
                .orderBy(price.sum().asc())
                .limit(1)
                .fetchOne();

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(brand)
                        .where(brand.id.eq(cheapestBrandId), brand.deleted.eq(false))
                        .fetchOne()
        );
    }
}
