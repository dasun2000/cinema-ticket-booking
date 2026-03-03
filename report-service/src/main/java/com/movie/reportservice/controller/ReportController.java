package com.movie.reportservice.controller;

import com.movie.reportservice.entity.Report;
import com.movie.reportservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reports")

public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() { return reportService.getAllReports(); }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) { return reportService.createReport(report); }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report) {
        try { return ResponseEntity.ok(reportService.updateReport(id, report)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public List<Report> getByType(@PathVariable String type) { return reportService.getByType(type); }

    @GetMapping("/movie/{movieId}")
    public List<Report> getByMovie(@PathVariable Long movieId) { return reportService.getByMovie(movieId); }
}
