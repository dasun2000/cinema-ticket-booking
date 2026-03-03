package com.movie.movieservice.service;

import com.movie.movieservice.entity.Movie;
import com.movie.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        movie.setCreatedAt(LocalDateTime.now());
        movie.setUpdatedAt(LocalDateTime.now());
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setGenre(updatedMovie.getGenre());
            movie.setLanguage(updatedMovie.getLanguage());
            movie.setDurationMinutes(updatedMovie.getDurationMinutes());
            movie.setDirector(updatedMovie.getDirector());
            movie.setCast(updatedMovie.getCast());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setPosterUrl(updatedMovie.getPosterUrl());
            movie.setRating(updatedMovie.getRating());
            movie.setStatus(updatedMovie.getStatus());
            movie.setUpdatedAt(LocalDateTime.now());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new RuntimeException("Movie not found: " + id));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getNowShowingMovies() {
        return movieRepository.findByStatus("NOW_SHOWING");
    }

    public List<Movie> searchMovies(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
}
