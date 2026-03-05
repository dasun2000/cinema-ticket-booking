package com.movie.showtimeservice.service;

import com.movie.showtimeservice.entity.Showtime;
import com.movie.showtimeservice.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    public List<Showtime> getAllShowtimes() { return showtimeRepository.findAll(); }
    public Optional<Showtime> getShowtimeById(Long id) { return showtimeRepository.findById(id); }

    public Showtime createShowtime(Showtime showtime) {
        showtime.setAvailableSeats(showtime.getTotalSeats());
        showtime.setCreatedAt(LocalDateTime.now());
        showtime.setUpdatedAt(LocalDateTime.now());
        return showtimeRepository.save(showtime);
    }

    public Showtime updateShowtime(Long id, Showtime updated) {
        return showtimeRepository.findById(id).map(s -> {
            s.setMovieId(updated.getMovieId());
            s.setTheaterId(updated.getTheaterId());
            s.setScreenNumber(updated.getScreenNumber());
            s.setShowDate(updated.getShowDate());
            s.setShowTime(updated.getShowTime());
            s.setTicketPrice(updated.getTicketPrice());
            s.setTotalSeats(updated.getTotalSeats());
            s.setAvailableSeats(updated.getAvailableSeats());
            s.setStatus(updated.getStatus());
            s.setUpdatedAt(LocalDateTime.now());
            return showtimeRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Showtime not found: " + id));
    }

    public void deleteShowtime(Long id) { showtimeRepository.deleteById(id); }
    public List<Showtime> getShowtimesByMovie(Long movieId) { return showtimeRepository.findByMovieId(movieId); }
    public List<Showtime> getShowtimesByDate(LocalDate date) { return showtimeRepository.findByShowDate(date); }
    public List<Showtime> getShowtimesByMovieAndDate(Long movieId, LocalDate date) {
        return showtimeRepository.findByMovieIdAndShowDate(movieId, date);
    }

    public Showtime updateAvailableSeats(Long id, int seatsToReduce) {
        return showtimeRepository.findById(id).map(s -> {
            s.setAvailableSeats(s.getAvailableSeats() - seatsToReduce);
            s.setUpdatedAt(LocalDateTime.now());
            return showtimeRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Showtime not found: " + id));
    }
}
