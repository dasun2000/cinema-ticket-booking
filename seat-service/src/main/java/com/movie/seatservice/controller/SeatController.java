package com.movie.seatservice.controller;

import com.movie.seatservice.entity.Seat;
import com.movie.seatservice.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/seats")

public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> getAllSeats() { return seatService.getAllSeats(); }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        return seatService.getSeatById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Seat createSeat(@RequestBody Seat seat) { return seatService.createSeat(seat); }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> updateSeat(@PathVariable Long id, @RequestBody Seat seat) {
        try { return ResponseEntity.ok(seatService.updateSeat(id, seat)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/showtime/{showtimeId}/available")
    public List<Seat> getAvailableSeats(@PathVariable Long showtimeId) {
        return seatService.getAvailableSeatsByShowtime(showtimeId);
    }

    @GetMapping("/showtime/{showtimeId}")
    public List<Seat> getSeatsByShowtime(@PathVariable Long showtimeId) {
        return seatService.getSeatsByShowtime(showtimeId);
    }

    @PatchMapping("/{seatId}/book/{bookingId}")
    public ResponseEntity<Seat> bookSeat(@PathVariable Long seatId, @PathVariable Long bookingId) {
        try { return ResponseEntity.ok(seatService.bookSeat(seatId, bookingId)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
