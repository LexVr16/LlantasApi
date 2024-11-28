package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.Product;
import com.maycollins.LlantasApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(Integer categoryId) {
        return productRepository.findByCategory_CategoryId(categoryId);
    }

    public void updateProductStock(Integer productId, Integer quantity) {
        Optional<Product> product = productRepository.findById(productId);
        product.ifPresent(p -> {
            p.updateProductStock(productId, quantity);
            productRepository.save(p);
        });
    }
}