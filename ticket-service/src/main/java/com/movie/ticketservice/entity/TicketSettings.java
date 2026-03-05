package com.movie.ticketservice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket_settings")
public class TicketSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer maxTicketLimit;

    public TicketSettings() {
    }

    public TicketSettings(Integer maxTicketLimit) {
        this.maxTicketLimit = maxTicketLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxTicketLimit() {
        return maxTicketLimit;
    }

    public void setMaxTicketLimit(Integer maxTicketLimit) {
        this.maxTicketLimit = maxTicketLimit;
    }
}
