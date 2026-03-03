package com.movie.seatservice.service;

import com.movie.seatservice.entity.Seat;
import com.movie.seatservice.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatById(Long id) {
        return seatRepository.findById(id);
    }

    public Seat createSeat(Seat seat) {
        seat.setCreatedAt(LocalDateTime.now());
        seat.setUpdatedAt(LocalDateTime.now());
        return seatRepository.save(seat);
    }

    public Seat updateSeat(Long id, Seat updated) {
        return seatRepository.findById(id).map(s -> {
            s.setSeatNumber(updated.getSeatNumber());
            s.setRow(updated.getRow());
            s.setSeatType(updated.getSeatType());
            s.setStatus(updated.getStatus());
            s.setPrice(updated.getPrice());
            s.setUpdatedAt(LocalDateTime.now());
            return seatRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Seat not found: " + id));
    }

    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }

    public List<Seat> getAvailableSeatsByShowtime(Long showtimeId) {
        return seatRepository.findByShowtimeIdAndStatus(showtimeId, "AVAILABLE");
    }

    public Seat bookSeat(Long seatId, Long bookingId) {
        return seatRepository.findById(seatId).map(s -> {
            s.setStatus("BOOKED");
            s.setBookingId(bookingId);
            s.setUpdatedAt(LocalDateTime.now());
            return seatRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Seat not found: " + seatId));
    }

    public List<Seat> getSeatsByShowtime(Long showtimeId) {
        return seatRepository.findByShowtimeId(showtimeId);
    }
}
