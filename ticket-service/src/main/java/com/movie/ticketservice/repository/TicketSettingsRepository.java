package com.movie.ticketservice.repository;

import com.movie.ticketservice.entity.TicketSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketSettingsRepository extends JpaRepository<TicketSettings, Long> {
}
