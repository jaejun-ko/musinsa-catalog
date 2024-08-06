package com.assignment.musinsacatalog.infrastructure.brand;

import com.assignment.musinsacatalog.domain.brand.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT b FROM Brand b WHERE b.deleted = false")
    List<Brand> findAllBrands();
}
