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

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        report.setCreatedAt(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public Report updateReportFull(Long id, Report updated) {
        return reportRepository.findById(id).map(r -> {
            r.setName(updated.getName());
            r.setFromDate(updated.getFromDate());
            r.setToDate(updated.getToDate());
            r.setReportType(updated.getReportType());
            r.setSchedule(updated.getSchedule());
            r.setStatus(updated.getStatus());
            return reportRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Report not found: " + id));
    }

    public Report updateReportPartial(Long id, Report updates) {
        return reportRepository.findById(id).map(r -> {
            if (updates.getName() != null)
                r.setName(updates.getName());
            if (updates.getFromDate() != null)
                r.setFromDate(updates.getFromDate());
            if (updates.getToDate() != null)
                r.setToDate(updates.getToDate());
            if (updates.getReportType() != null)
                r.setReportType(updates.getReportType());
            if (updates.getSchedule() != null)
                r.setSchedule(updates.getSchedule());
            if (updates.getStatus() != null)
                r.setStatus(updates.getStatus());
            return reportRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Report not found: " + id));
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public List<Report> getByType(String type) {
        return reportRepository.findByReportType(type);
    }

    public List<Report> getByMovie(Long movieId) {
        return reportRepository.findByMovieId(movieId);
    }
}
