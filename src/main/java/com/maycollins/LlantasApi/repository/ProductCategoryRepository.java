package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    boolean existsByCategoryName(String categoryName);
}