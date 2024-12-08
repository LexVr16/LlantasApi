package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.Receipt;
import com.maycollins.LlantasApi.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable Integer id) {
        return receiptService.getReceiptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Receipt createReceipt(@RequestBody Receipt receipt) {
        return receiptService.createReceipt(receipt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receipt> updateReceipt(
            @PathVariable Integer id,
            @RequestBody Receipt receipt) {
        return receiptService.getReceiptById(id)
                .map(existingReceipt -> {
                    receipt.setReceiptId(id);
                    return ResponseEntity.ok(receiptService.updateReceipt(receipt));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Integer id) {
        return receiptService.getReceiptById(id)
                .map(receipt -> {
                    receiptService.deleteReceipt(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public List<Receipt> getReceiptsByClient(@PathVariable Integer clientId) {
        return receiptService.getReceiptsByClient(clientId);
    }

    @GetMapping("/status/{status}")
    public List<Receipt> getReceiptsByStatus(@PathVariable String status) {
        return receiptService.getReceiptsByStatus(status);
    }

    @GetMapping("/type/{type}")
    public List<Receipt> getReceiptsByType(@PathVariable String type) {
        return receiptService.getReceiptsByType(type);
    }

    @GetMapping("/date-range")
    public List<Receipt> getReceiptsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return receiptService.getReceiptsByDateRange(startDate, endDate);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReceipt(@PathVariable Integer id) {
        return receiptService.getReceiptById(id)
                .map(receipt -> {
                    receiptService.cancelReceipt(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/void")
    public ResponseEntity<Void> voidReceipt(@PathVariable Integer id) {
        return receiptService.getReceiptById(id)
                .map(receipt -> {
                    receiptService.voidReceipt(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{receiptNumber}")
    public ResponseEntity<Receipt> getReceiptByNumber(@PathVariable String receiptNumber) {
        Receipt receipt = receiptService.findByReceiptNumber(receiptNumber);
        return receipt != null ? ResponseEntity.ok(receipt) : ResponseEntity.notFound().build();
    }
}