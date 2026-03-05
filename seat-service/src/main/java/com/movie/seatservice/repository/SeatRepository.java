package com.movie.seatservice.repository;

import com.movie.seatservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByShowtimeId(Long showtimeId);
    List<Seat> findByShowtimeIdAndStatus(Long showtimeId, String status);
    List<Seat> findByBookingId(Long bookingId);
    List<Seat> findByTheaterIdAndStatus(Long theaterId, String status);
}
