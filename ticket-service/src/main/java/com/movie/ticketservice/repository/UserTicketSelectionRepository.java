package com.movie.ticketservice.repository;

import com.movie.ticketservice.entity.UserTicketSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTicketSelectionRepository extends JpaRepository<UserTicketSelection, Long> {
}
