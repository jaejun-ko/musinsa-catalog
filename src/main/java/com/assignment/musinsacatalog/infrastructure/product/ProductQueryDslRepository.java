package com.assignment.musinsacatalog.infrastructure.product;

import com.assignment.musinsacatalog.domain.product.Product;
import com.assignment.musinsacatalog.domain.product.QProduct;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<Product> findLowestPriceProductsOfEachCategory() {

        QProduct product = QProduct.product;
        QProduct subProduct = new QProduct("subProduct");
        QProduct subProduct2 = new QProduct("subProduct2");

        return queryFactory
                .selectFrom(product)
                .where(
                        product.deleted.eq(false),
                        product.price.eq(
                                JPAExpressions
                                        .select(subProduct.price.min())
                                        .from(subProduct)
                                        .where(subProduct.category.id.eq(product.category.id)
                                                .and(subProduct.deleted.eq(false)))
                        ),
                        product.brand.id.eq(
                                JPAExpressions
                                        .select(subProduct2.brand.id.max())
                                        .from(subProduct2)
                                        .where(subProduct2.category.id.eq(product.category.id)
                                                .and(subProduct2.price.eq(product.price))
                                                .and(subProduct2.deleted.eq(false)))
                        )
                )
                .orderBy(product.category.id.asc())
                .fetch();
    }

    public List<Product> findLowestTotalPriceProductsByBrandId(Long brandId) {

        QProduct product = QProduct.product;
        QProduct subProduct = new QProduct("subProduct");

        return queryFactory
                .selectFrom(product)
                .where(
                        product.brand.id.eq(brandId),
                        product.deleted.eq(false),
                        product.price.eq(
                                JPAExpressions
                                        .select(subProduct.price.min())
                                        .from(subProduct)
                                        .where(
                                                subProduct.category.id.eq(product.category.id),
                                                subProduct.brand.id.eq(product.brand.id),
                                                subProduct.deleted.eq(false)
                                        )
                        ),
                        product.id.eq(
                                JPAExpressions
                                        .select(subProduct.id.max())
                                        .from(subProduct)
                                        .where(
                                                subProduct.category.id.eq(product.category.id),
                                                subProduct.brand.id.eq(product.brand.id),
                                                subProduct.price.eq(product.price),
                                                subProduct.deleted.eq(false)
                                        )
                        )
                )
                .orderBy(product.category.id.asc())
                .fetch();
    }

    public List<Product> findLowestPriceProductsByCategoryId(Long categoryId) {

        QProduct product = QProduct.product;

        return queryFactory
                .selectFrom(product)
                .where(
                        product.price.eq(
                                JPAExpressions
                                        .select(product.price.min())
                                        .from(product)
                                        .where(
                                                product.category.id.eq(categoryId),
                                                product.deleted.eq(false)
                                        )
                        ),
                        product.category.id.eq(categoryId)
                )
                .orderBy(product.id.asc())
                .fetch();

    }

    public List<Product> findHighestPriceProductsByCategoryId(Long categoryId) {

        QProduct product = QProduct.product;
        return queryFactory
                .selectFrom(product)
                .where(
                        product.price.eq(
                                JPAExpressions
                                        .select(product.price.max())
                                        .from(product)
                                        .where(
                                                product.category.id.eq(categoryId),
                                                product.deleted.eq(false)
                                        )
                        ),
                        product.category.id.eq(categoryId)
                )
                .orderBy(product.id.asc())
                .fetch();
    }
}
