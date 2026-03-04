package com.movie.bookingservice.service;

import com.movie.bookingservice.entity.Booking;
import com.movie.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() { return bookingRepository.findAll(); }
    public Optional<Booking> getBookingById(Long id) { return bookingRepository.findById(id); }

    public Booking createBooking(Booking booking) {
        booking.setBookingReference("BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setStatus("CONFIRMED");
        booking.setCreatedAt(LocalDateTime.now());
        booking.setUpdatedAt(LocalDateTime.now());
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updated) {
        return bookingRepository.findById(id).map(b -> {
            b.setStatus(updated.getStatus());
            b.setUpdatedAt(LocalDateTime.now());
            return bookingRepository.save(b);
        }).orElseThrow(() -> new RuntimeException("Booking not found: " + id));
    }

    public void deleteBooking(Long id) { bookingRepository.deleteById(id); }

    public Booking cancelBooking(Long id) {
        return bookingRepository.findById(id).map(b -> {
            b.setStatus("CANCELLED");
            b.setUpdatedAt(LocalDateTime.now());
            return bookingRepository.save(b);
        }).orElseThrow(() -> new RuntimeException("Booking not found: " + id));
    }

    public List<Booking> getBookingsByUser(Long userId) { return bookingRepository.findByUserId(userId); }
    public Optional<Booking> getBookingByReference(String ref) { return bookingRepository.findByBookingReference(ref); }
}
