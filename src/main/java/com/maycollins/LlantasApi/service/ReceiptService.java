package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.Receipt;
import com.maycollins.LlantasApi.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepository;

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Optional<Receipt> getReceiptById(Integer id) {
        return receiptRepository.findById(id);
    }

    public Receipt createReceipt(Receipt receipt) {
        // Generate unique receipt number
        receipt.setReceiptNumber(generateReceiptNumber());
        receipt.setIssueDate(new Date());
        receipt.setReceiptStatus("Issued");
        receipt.generateReceipt();
        return receiptRepository.save(receipt);
    }

    public Receipt updateReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public void deleteReceipt(Integer id) {
        receiptRepository.deleteById(id);
    }

    public List<Receipt> getReceiptsByClient(Integer clientId) {
        return receiptRepository.findByClient_ClientId(clientId);
    }

    public List<Receipt> getReceiptsByStatus(String status) {
        return receiptRepository.findByReceiptStatus(status);
    }

    public List<Receipt> getReceiptsByType(String type) {
        return receiptRepository.findByReceiptType(type);
    }

    public List<Receipt> getReceiptsByDateRange(Date startDate, Date endDate) {
        return receiptRepository.findByIssueDateBetween(startDate, endDate);
    }

    public void cancelReceipt(Integer id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        receipt.ifPresent(r -> {
            r.cancelReceipt();
            receiptRepository.save(r);
        });
    }

    public void voidReceipt(Integer id) {
        Optional<Receipt> receipt = receiptRepository.findById(id);
        receipt.ifPresent(r -> {
            r.voidReceipt();
            receiptRepository.save(r);
        });
    }

    private String generateReceiptNumber() {
        return "RCP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Receipt findByReceiptNumber(String receiptNumber) {
        return receiptRepository.findByReceiptNumber(receiptNumber);
    }
}