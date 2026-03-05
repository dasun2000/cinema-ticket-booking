package com.movie.ticketservice.repository;

import com.movie.ticketservice.entity.TicketOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketOptionRepository extends JpaRepository<TicketOption, Long> {
    Optional<TicketOption> findByTicketCount(Integer ticketCount);
}
