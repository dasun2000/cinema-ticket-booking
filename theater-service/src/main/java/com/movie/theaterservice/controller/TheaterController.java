package com.movie.theaterservice.controller;

import com.movie.theaterservice.entity.Theater;
import com.movie.theaterservice.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/theaters")

public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public List<Theater> getAllTheaters() { return theaterService.getAllTheaters(); }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        return theaterService.getTheaterById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Theater createTheater(@RequestBody Theater theater) { return theaterService.createTheater(theater); }

    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @RequestBody Theater theater) {
        try { return ResponseEntity.ok(theaterService.updateTheater(id, theater)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/city/{city}")
    public List<Theater> getByCity(@PathVariable String city) { return theaterService.getTheatersByCity(city); }

    @GetMapping("/active")
    public List<Theater> getActiveTheaters() { return theaterService.getActiveTheaters(); }
}
