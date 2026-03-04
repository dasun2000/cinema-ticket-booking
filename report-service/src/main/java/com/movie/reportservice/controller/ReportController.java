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
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.createReport(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReportFull(@PathVariable Long id, @RequestBody Report report) {
        try {
            return ResponseEntity.ok(reportService.updateReportFull(id, report));
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (e.getMessage() != null && e.getMessage().contains("Report not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(500)
                    .body(e.getMessage() != null ? e.getMessage() : "An unexpected error occurred");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateReportPartial(@PathVariable Long id, @RequestBody Report report) {
        try {
            return ResponseEntity.ok(reportService.updateReportPartial(id, report));
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (e.getMessage() != null && e.getMessage().contains("Report not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(500)
                    .body(e.getMessage() != null ? e.getMessage() : "An unexpected error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public List<Report> getByType(@PathVariable String type) {
        return reportService.getByType(type);
    }

    @GetMapping("/movie/{movieId}")
    public List<Report> getByMovie(@PathVariable Long movieId) {
        return reportService.getByMovie(movieId);
    }
}
