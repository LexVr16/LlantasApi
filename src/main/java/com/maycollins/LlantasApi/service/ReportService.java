package com.maycollins.LlantasApi.service;

import com.maycollins.LlantasApi.model.Report;
import com.maycollins.LlantasApi.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Integer id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        report.setGenerationDate(new Date());
        report.generateReport();
        return reportRepository.save(report);
    }

    public Report updateReport(Report report) {
        report.updateReport();
        return reportRepository.save(report);
    }

    public void deleteReport(Integer id) {
        reportRepository.deleteById(id);
    }

    public List<Report> getReportsByUser(Integer userId) {
        return reportRepository.findByUser_UserId(userId);
    }

    public List<Report> getReportsByType(String reportType) {
        return reportRepository.findByReportType(reportType);
    }

    public List<Report> getReportsByCategory(String category) {
        return reportRepository.findByCategory(category);
    }

    public List<Report> getReportsByDateRange(Date startDate, Date endDate) {
        return reportRepository.findByGenerationDateBetween(startDate, endDate);
    }

    public void generateStatistics(Integer reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        report.ifPresent(r -> {
            r.displayStatistics();
            reportRepository.save(r);
        });
    }

    public void exportReport(Integer reportId, String format) {
        Optional<Report> report = reportRepository.findById(reportId);
        report.ifPresent(r -> {
            r.exportReport(format);
            reportRepository.save(r);
        });
    }

    public void generateGraphics(Integer reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        report.ifPresent(r -> {
            r.generateGraphics();
            reportRepository.save(r);
        });
    }

    public void calculateMetrics(Integer reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        report.ifPresent(r -> {
            r.calculateMetrics();
            reportRepository.save(r);
        });
    }

    public void comparePeriods(Integer reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        report.ifPresent(r -> {
            r.compareWithPreviousPeriod();
            reportRepository.save(r);
        });
    }

    public void calculatePredictions(Integer reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        report.ifPresent(r -> {
            r.calculatePredictions();
            reportRepository.save(r);
        });
    }
}