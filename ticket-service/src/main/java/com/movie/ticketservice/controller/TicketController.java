package com.movie.ticketservice.controller;

import com.movie.ticketservice.entity.Ticket;
import com.movie.ticketservice.entity.UserTicketSelection;
import com.movie.ticketservice.repository.UserTicketSelectionRepository;
import com.movie.ticketservice.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserTicketSelectionRepository selectionRepository;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/booking/{bookingReference}")
    public List<Ticket> getTicketsByBookingReference(@PathVariable String bookingReference) {
        return ticketService.getTicketsByBookingReference(bookingReference);
    }

    @GetMapping("/showtime/{showtimeId}")
    public List<Ticket> getTicketsByShowtime(@PathVariable Long showtimeId) {
        return ticketService.getTicketsByShowtimeId(showtimeId);
    }

    @PostMapping("/issue")
    public List<Ticket> issueTickets(@RequestBody Map<String, Object> payload) {
        String bookingRef = (String) payload.get("bookingReference");
        Long showtimeId = ((Number) payload.get("showtimeId")).longValue();
        List<String> seatNumbers = (List<String>) payload.get("seatNumbers");

        return ticketService.issueTickets(bookingRef, showtimeId, seatNumbers);
    }

    @PostMapping("/selection")
    public ResponseEntity<UserTicketSelection> saveSelection(@RequestBody Map<String, Object> payload) {
        Integer count = (Integer) payload.get("ticketCount");

        UserTicketSelection selection = new UserTicketSelection();
        selection.setTicketDate(LocalDate.now());
        selection.setTicketTime(LocalTime.now());
        selection.setTicketCount(count);

        return ResponseEntity.ok(selectionRepository.save(selection));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Ticket> updateTicketStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            return ResponseEntity.ok(ticketService.updateTicketStatus(id, status));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
