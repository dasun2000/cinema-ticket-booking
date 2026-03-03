package com.movie.ticketservice.service;

import com.movie.ticketservice.entity.Ticket;
import com.movie.ticketservice.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getTicketsByBookingReference(String bookingReference) {
        return ticketRepository.findByBookingReference(bookingReference);
    }

    public List<Ticket> getTicketsByShowtimeId(Long showtimeId) {
        return ticketRepository.findByShowtimeId(showtimeId);
    }

    // Creates tickets and generates QR
    public List<Ticket> issueTickets(String bookingReference, Long showtimeId, List<String> seatNumbers) {
        List<Ticket> tickets = seatNumbers.stream().map(seat -> {
            Ticket ticket = new Ticket();
            ticket.setBookingReference(bookingReference);
            ticket.setShowtimeId(showtimeId);
            ticket.setSeatNumber(seat);
            ticket.setStatus("VALID");
            ticket.setIssuedAt(LocalDateTime.now());
            // mock QR Code string
            ticket.setQrCode(
                    "QR-" + UUID.randomUUID().toString().substring(0, 8) + "-" + bookingReference + "-" + seat);
            return ticket;
        }).toList();

        return ticketRepository.saveAll(tickets);
    }

    public Ticket updateTicketStatus(Long id, String status) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setStatus(status);
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new RuntimeException("Ticket not found: " + id));
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
