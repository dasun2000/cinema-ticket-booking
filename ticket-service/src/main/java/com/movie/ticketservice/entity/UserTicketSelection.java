package com.movie.ticketservice.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "user_ticket_selection")
public class UserTicketSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private LocalDate ticketDate;

    private LocalTime ticketTime;

    private Integer ticketCount;

    public UserTicketSelection() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    public LocalTime getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(LocalTime ticketTime) {
        this.ticketTime = ticketTime;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }
}
