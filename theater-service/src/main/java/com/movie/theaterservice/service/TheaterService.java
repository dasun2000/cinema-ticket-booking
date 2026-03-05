package com.movie.theaterservice.service;

import com.movie.theaterservice.entity.Theater;
import com.movie.theaterservice.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public List<Theater> getAllTheaters() { return theaterRepository.findAll(); }
    public Optional<Theater> getTheaterById(Long id) { return theaterRepository.findById(id); }

    public Theater createTheater(Theater theater) {
        theater.setCreatedAt(LocalDateTime.now());
        theater.setUpdatedAt(LocalDateTime.now());
        return theaterRepository.save(theater);
    }

    public Theater updateTheater(Long id, Theater updatedTheater) {
        return theaterRepository.findById(id).map(theater -> {
            theater.setName(updatedTheater.getName());
            theater.setLocation(updatedTheater.getLocation());
            theater.setCity(updatedTheater.getCity());
            theater.setAddress(updatedTheater.getAddress());
            theater.setTotalScreens(updatedTheater.getTotalScreens());
            theater.setContactNumber(updatedTheater.getContactNumber());
            theater.setEmail(updatedTheater.getEmail());
            theater.setActive(updatedTheater.isActive());
            theater.setUpdatedAt(LocalDateTime.now());
            return theaterRepository.save(theater);
        }).orElseThrow(() -> new RuntimeException("Theater not found: " + id));
    }

    public void deleteTheater(Long id) { theaterRepository.deleteById(id); }
    public List<Theater> getTheatersByCity(String city) { return theaterRepository.findByCity(city); }
    public List<Theater> getActiveTheaters() { return theaterRepository.findByActiveTrue(); }
}
