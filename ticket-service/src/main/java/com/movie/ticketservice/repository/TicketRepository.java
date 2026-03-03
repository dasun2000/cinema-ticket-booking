package com.movie.ticketservice.repository;

import com.movie.ticketservice.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByBookingReference(String bookingReference);

    List<Ticket> findByShowtimeId(Long showtimeId);
}
