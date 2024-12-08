package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.Buy;
import com.maycollins.LlantasApi.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class BuyController {
    @Autowired
    private BuyService buyService;

    @GetMapping
    public List<Buy> getAllPurchases() {
        return buyService.getAllPurchases();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buy> getPurchaseById(@PathVariable Integer id) {
        return buyService.getPurchaseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Buy createPurchase(@RequestBody Buy purchase) {
        return buyService.createPurchase(purchase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buy> updatePurchase(
            @PathVariable Integer id,
            @RequestBody Buy purchase) {
        return buyService.getPurchaseById(id)
                .map(existingPurchase -> {
                    purchase.setPurchaseId(id);
                    return ResponseEntity.ok(buyService.updatePurchase(purchase));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Integer id) {
        return buyService.getPurchaseById(id)
                .map(purchase -> {
                    buyService.deletePurchase(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}")
    public List<Buy> getPurchasesByProduct(@PathVariable Integer productId) {
        return buyService.getPurchasesByProduct(productId);
    }

    @GetMapping("/date-range")
    public List<Buy> getPurchasesByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return buyService.getPurchasesByDateRange(startDate, endDate);
    }

    @GetMapping("/status/{status}")
    public List<Buy> getPurchasesByStatus(@PathVariable String status) {
        return buyService.getPurchasesByStatus(status);
    }

    @PostMapping("/{id}/restock")
    public ResponseEntity<Void> restockProduct(@PathVariable Integer id) {
        return buyService.getPurchaseById(id)
                .map(purchase -> {
                    buyService.restockProduct(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}