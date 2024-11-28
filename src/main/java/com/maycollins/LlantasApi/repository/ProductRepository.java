package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_CategoryId(Integer categoryId);
    boolean existsByProductSerial(String productSerial);
}