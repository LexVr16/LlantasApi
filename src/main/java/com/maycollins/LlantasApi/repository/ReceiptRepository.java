package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findByClient_ClientId(Integer clientId);
    List<Receipt> findByReceiptStatus(String status);
    List<Receipt> findByReceiptType(String type);
    List<Receipt> findByIssueDateBetween(Date startDate, Date endDate);
    List<Receipt> findByTotalAmountGreaterThan(Double amount);
    Receipt findByReceiptNumber(String receiptNumber);
}