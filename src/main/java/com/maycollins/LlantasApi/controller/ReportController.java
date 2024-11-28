package com.maycollins.LlantasApi.controller;

import com.maycollins.LlantasApi.model.Report;
import com.maycollins.LlantasApi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(
            @PathVariable Integer id,
            @RequestBody Report report) {
        return reportService.getReportById(id)
                .map(existingReport -> {
                    report.setReportId(id);
                    return ResponseEntity.ok(reportService.updateReport(report));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        return reportService.getReportById(id)
                .map(report -> {
                    reportService.deleteReport(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Report> getReportsByUser(@PathVariable Integer userId) {
        return reportService.getReportsByUser(userId);
    }

    @GetMapping("/type/{reportType}")
    public List<Report> getReportsByType(@PathVariable String reportType) {
        return reportService.getReportsByType(reportType);
    }

    @GetMapping("/category/{category}")
    public List<Report> getReportsByCategory(@PathVariable String category) {
        return reportService.getReportsByCategory(category);
    }

    @GetMapping("/date-range")
    public List<Report> getReportsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return reportService.getReportsByDateRange(startDate, endDate);
    }

    @PostMapping("/{id}/statistics")
    public ResponseEntity<Void> generateStatistics(@PathVariable Integer id) {
        reportService.generateStatistics(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/export")
    public ResponseEntity<Void> exportReport(
            @PathVariable Integer id,
            @RequestParam String format) {
        reportService.exportReport(id, format);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/graphics")
    public ResponseEntity<Void> generateGraphics(@PathVariable Integer id) {
        reportService.generateGraphics(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/metrics")
    public ResponseEntity<Void> calculateMetrics(@PathVariable Integer id) {
        reportService.calculateMetrics(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/compare")
    public ResponseEntity<Void> comparePeriods(@PathVariable Integer id) {
        reportService.comparePeriods(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/predictions")
    public ResponseEntity<Void> calculatePredictions(@PathVariable Integer id) {
        reportService.calculatePredictions(id);
        return ResponseEntity.ok().build();
    }
}