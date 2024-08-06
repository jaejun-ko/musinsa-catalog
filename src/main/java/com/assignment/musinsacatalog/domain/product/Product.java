package com.assignment.musinsacatalog.domain.product;

import com.assignment.musinsacatalog.common.exception.InvalidParamException;
import com.assignment.musinsacatalog.domain.brand.Brand;
import com.assignment.musinsacatalog.domain.category.Category;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.YesNoConverter;

@Getter
@Entity
@Table(
        name = "products",
        indexes = {
                @Index(name = "idx_products_brand_id", columnList = "brand_id"),
                @Index(name = "idx_products_category_id", columnList = "category_id"),
        }
)
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private Brand brand;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    private Long price;
    @Convert(converter = YesNoConverter.class)
    private Boolean deleted;

    @Builder
    public Product(Brand brand, Category category, Long price) {

        if (brand == null) throw new InvalidParamException("Product.brand");
        if (category == null) throw new InvalidParamException("Product.category");
        if (price == null || price < 0) throw new InvalidParamException("Product.price");

        this.brand = brand;
        this.category = category;
        this.price = price;
        this.deleted = Boolean.FALSE;
    }

    public void modify(Brand brand, Category category, Long price) {

        this.changeBrand(brand);
        this.changeCategory(category);
        this.changePrice(price);
    }

    public void changeBrand(Brand brand) {

        if (brand == null) throw new InvalidParamException("Product.brand");

        this.brand = brand;
    }

    public void changeCategory(Category category) {

        if (category == null) throw new InvalidParamException("Product.category");

        this.category = category;
    }

    public void changePrice(Long price) {

        if (price == null || price < 0) throw new InvalidParamException("Product.price");

        this.price = price;
    }

    public void delete() {

        this.deleted = Boolean.TRUE;
    }
}
