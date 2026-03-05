package com.movie.ticketservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_options")
public class TicketOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ticketCount;

    private String iconUrl;

    public TicketOption() {
    }

    public TicketOption(Integer ticketCount, String iconUrl) {
        this.ticketCount = ticketCount;
        this.iconUrl = iconUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
