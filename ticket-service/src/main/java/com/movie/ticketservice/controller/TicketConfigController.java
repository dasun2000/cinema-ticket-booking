package com.movie.ticketservice.controller;

import com.movie.ticketservice.entity.TicketOption;
import com.movie.ticketservice.entity.TicketSettings;
import com.movie.ticketservice.service.TicketConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tickets/config")
public class TicketConfigController {

    @Autowired
    private TicketConfigService configService;

    // ----- Options -----
    @GetMapping("/options")
    public List<TicketOption> getOptions() {
        return configService.getAllTicketOptions();
    }

    @PostMapping("/options")
    public TicketOption addOption(@RequestBody TicketOption option) {
        return configService.saveTicketOption(option);
    }

    @PutMapping("/options/{id}/icon")
    public ResponseEntity<TicketOption> updateIcon(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(configService.updateTicketIcon(id, body.get("iconUrl")));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/options/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        configService.deleteTicketOption(id);
        return ResponseEntity.noContent().build();
    }

    // ----- Settings -----
    @GetMapping("/settings")
    public TicketSettings getSettings() {
        return configService.getTicketSettings();
    }

    @PutMapping("/settings/limit")
    public TicketSettings updateLimit(@RequestBody Map<String, Integer> body) {
        return configService.updateTicketLimit(body.get("maxTicketLimit"));
    }
}
