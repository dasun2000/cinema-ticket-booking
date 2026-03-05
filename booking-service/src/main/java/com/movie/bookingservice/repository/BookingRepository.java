package com.movie.bookingservice.repository;

import com.movie.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByShowtimeId(Long showtimeId);
    Optional<Booking> findByBookingReference(String bookingReference);
    List<Booking> findByStatus(String status);
}
