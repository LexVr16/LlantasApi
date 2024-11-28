package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.ProductCategory;
import com.maycollins.LlantasApi.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping
    public List<ProductCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductCategory createCategory(@RequestBody ProductCategory category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> updateCategory(
            @PathVariable Integer id,
            @RequestBody ProductCategory category) {
        return categoryService.getCategoryById(id)
                .map(existingCategory -> {
                    category.setCategoryId(id);
                    return ResponseEntity.ok(categoryService.updateCategory(category));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        return categoryService.getCategoryById(id)
                .map(category -> {
                    categoryService.deleteCategory(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
