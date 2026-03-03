package com.movie.showtimeservice.controller;

import com.movie.showtimeservice.entity.Showtime;
import com.movie.showtimeservice.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/showtimes")

public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @GetMapping
    public List<Showtime> getAllShowtimes() { return showtimeService.getAllShowtimes(); }

    @GetMapping("/{id}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long id) {
        return showtimeService.getShowtimeById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Showtime createShowtime(@RequestBody Showtime showtime) { return showtimeService.createShowtime(showtime); }

    @PutMapping("/{id}")
    public ResponseEntity<Showtime> updateShowtime(@PathVariable Long id, @RequestBody Showtime showtime) {
        try { return ResponseEntity.ok(showtimeService.updateShowtime(id, showtime)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
        showtimeService.deleteShowtime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie/{movieId}")
    public List<Showtime> getByMovie(@PathVariable Long movieId) { return showtimeService.getShowtimesByMovie(movieId); }

    @GetMapping("/date/{date}")
    public List<Showtime> getByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return showtimeService.getShowtimesByDate(date);
    }

    @GetMapping("/movie/{movieId}/date/{date}")
    public List<Showtime> getByMovieAndDate(
            @PathVariable Long movieId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return showtimeService.getShowtimesByMovieAndDate(movieId, date);
    }

    @PatchMapping("/{id}/reduce-seats/{count}")
    public ResponseEntity<Showtime> reduceSeats(@PathVariable Long id, @PathVariable int count) {
        try { return ResponseEntity.ok(showtimeService.updateAvailableSeats(id, count)); }
        catch (RuntimeException e) { return ResponseEntity.badRequest().build(); }
    }
}
