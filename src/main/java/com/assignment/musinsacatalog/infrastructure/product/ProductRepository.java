package com.assignment.musinsacatalog.infrastructure.product;

import com.assignment.musinsacatalog.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.deleted = false")
    List<Product> findAllProducts();
}
