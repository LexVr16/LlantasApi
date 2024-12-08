package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Integer> {
    List<Buy> findByProduct_ProductId(Integer productId);
    List<Buy> findByPurchaseDateBetween(Date startDate, Date endDate);
    List<Buy> findByPurchaseStatus(String status);
}