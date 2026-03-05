package com.movie.seatservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long showtimeId;
    private Long theaterId;
    private String seatNumber;
    @Column(name = "seat_row")
    private String row;
    private String seatType = "STANDARD";
    private String status = "AVAILABLE";
    private Long bookingId;
    private Double price;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Seat(Long id, Long showtimeId, Long theaterId, String seatNumber, String row, String seatType, String status, Long bookingId, Double price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.showtimeId = showtimeId;
        this.theaterId = theaterId;
        this.seatNumber = seatNumber;
        this.row = row;
        this.seatType = seatType;
        this.status = status;
        this.bookingId = bookingId;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(Long showtimeId) {
        this.showtimeId = showtimeId;
    }

    public Long getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(Long theaterId) {
        this.theaterId = theaterId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", showtimeId=" + showtimeId +
                ", theaterId=" + theaterId +
                ", seatNumber='" + seatNumber + '\'' +
                ", row='" + row + '\'' +
                ", seatType='" + seatType + '\'' +
                ", status='" + status + '\'' +
                ", bookingId=" + bookingId +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
