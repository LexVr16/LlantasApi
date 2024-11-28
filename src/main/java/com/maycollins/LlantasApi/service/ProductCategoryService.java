package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.ProductCategory;
import com.maycollins.LlantasApi.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository categoryRepository;

    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<ProductCategory> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    public ProductCategory createCategory(ProductCategory category) {
        return categoryRepository.save(category);
    }

    public ProductCategory updateCategory(ProductCategory category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}