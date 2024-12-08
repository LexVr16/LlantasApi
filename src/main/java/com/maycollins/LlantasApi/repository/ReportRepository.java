package com.maycollins.LlantasApi.repository;

import com.maycollins.LlantasApi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findByUser_UserId(Integer userId);
    List<Report> findByReportType(String reportType);
    List<Report> findByCategory(String category);
    List<Report> findByGenerationDateBetween(Date startDate, Date endDate);
    List<Report> findByProductId(Integer productId);
    List<Report> findByWarehouseId(Integer warehouseId);
}