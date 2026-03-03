package com.movie.reportservice.service;

import com.movie.reportservice.entity.Report;
import com.movie.reportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() { return reportRepository.findAll(); }
    public Optional<Report> getReportById(Long id) { return reportRepository.findById(id); }

    public Report createReport(Report report) {
        report.setCreatedAt(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report updated) {
        return reportRepository.findById(id).map(r -> {
            r.setReportType(updated.getReportType());
            r.setTotalBookings(updated.getTotalBookings());
            r.setTotalRevenue(updated.getTotalRevenue());
            r.setReportData(updated.getReportData());
            return reportRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Report not found: " + id));
    }

    public void deleteReport(Long id) { reportRepository.deleteById(id); }
    public List<Report> getByType(String type) { return reportRepository.findByReportType(type); }
    public List<Report> getByMovie(Long movieId) { return reportRepository.findByMovieId(movieId); }
}
