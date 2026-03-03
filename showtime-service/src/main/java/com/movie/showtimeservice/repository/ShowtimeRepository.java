package com.movie.showtimeservice.repository;

import com.movie.showtimeservice.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieId(Long movieId);
    List<Showtime> findByTheaterId(Long theaterId);
    List<Showtime> findByShowDate(LocalDate showDate);
    List<Showtime> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);
    List<Showtime> findByStatus(String status);
}
