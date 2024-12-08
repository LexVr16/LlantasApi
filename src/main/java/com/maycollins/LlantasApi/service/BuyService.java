package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.Buy;
import com.maycollins.LlantasApi.repository.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BuyService {
    @Autowired
    private BuyRepository buyRepository;

    @Autowired
    private ProductService productService;

    public List<Buy> getAllPurchases() {
        return buyRepository.findAll();
    }

    public Optional<Buy> getPurchaseById(Integer id) {
        return buyRepository.findById(id);
    }

    public Buy createPurchase(Buy purchase) {
        // Set current date
        purchase.setPurchaseDate(new Date());

        // Set initial status
        purchase.setPurchaseStatus("Pending");

        // Calculate total amount
        purchase.setTotalAmount(calculateTotalAmount(purchase));

        return buyRepository.save(purchase);
    }

    public Buy updatePurchase(Buy purchase) {
        return buyRepository.save(purchase);
    }

    public void deletePurchase(Integer id) {
        buyRepository.deleteById(id);
    }

    public List<Buy> getPurchasesByProduct(Integer productId) {
        return buyRepository.findByProduct_ProductId(productId);
    }

    public List<Buy> getPurchasesByDateRange(Date startDate, Date endDate) {
        return buyRepository.findByPurchaseDateBetween(startDate, endDate);
    }

    public List<Buy> getPurchasesByStatus(String status) {
        return buyRepository.findByPurchaseStatus(status);
    }

    private Double calculateTotalAmount(Buy purchase) {
        return purchase.getProduct().getPrice() * purchase.getQuantity();
    }

    public void restockProduct(Integer purchaseId) {
        Optional<Buy> purchase = buyRepository.findById(purchaseId);
        purchase.ifPresent(p -> {
            p.restockProduct();
            buyRepository.save(p);
        });
    }
}