package com.movie.reportservice.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportType; // SALES, BOOKINGS, REVENUE, MOVIE_PERFORMANCE
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long movieId;
    private Long theaterId;
    private Integer totalBookings;
    private Double totalRevenue;
    private String generatedBy;
    @Column(columnDefinition = "TEXT")
    private String reportData; // JSON summary

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public LocalDate getFromDate() { return fromDate; }
    public void setFromDate(LocalDate fromDate) { this.fromDate = fromDate; }
    public LocalDate getToDate() { return toDate; }
    public void setToDate(LocalDate toDate) { this.toDate = toDate; }
    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
    public Long getTheaterId() { return theaterId; }
    public void setTheaterId(Long theaterId) { this.theaterId = theaterId; }
    public Integer getTotalBookings() { return totalBookings; }
    public void setTotalBookings(Integer totalBookings) { this.totalBookings = totalBookings; }
    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }
    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
    public String getReportData() { return reportData; }
    public void setReportData(String reportData) { this.reportData = reportData; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
