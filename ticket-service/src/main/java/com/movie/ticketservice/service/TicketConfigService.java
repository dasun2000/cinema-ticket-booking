package com.movie.ticketservice.service;

import com.movie.ticketservice.entity.TicketOption;
import com.movie.ticketservice.entity.TicketSettings;
import com.movie.ticketservice.repository.TicketOptionRepository;
import com.movie.ticketservice.repository.TicketSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketConfigService {

    @Autowired
    private TicketOptionRepository ticketOptionRepository;

    @Autowired
    private TicketSettingsRepository ticketSettingsRepository;

    // Ticket Options
    public List<TicketOption> getAllTicketOptions() {
        return ticketOptionRepository.findAll();
    }

    public TicketOption saveTicketOption(TicketOption ticketOption) {
        return ticketOptionRepository.save(ticketOption);
    }

    public void deleteTicketOption(Long id) {
        ticketOptionRepository.deleteById(id);
    }

    public TicketOption updateTicketIcon(Long id, String iconUrl) {
        TicketOption option = ticketOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option not found"));
        option.setIconUrl(iconUrl);
        return ticketOptionRepository.save(option);
    }

    // Ticket Settings (Limit)
    public TicketSettings getTicketSettings() {
        return ticketSettingsRepository.findAll().stream().findFirst()
                .orElse(new TicketSettings(10));
    }

    public TicketSettings updateTicketLimit(Integer limit) {
        TicketSettings settings = ticketSettingsRepository.findAll().stream().findFirst()
                .orElse(new TicketSettings());
        settings.setMaxTicketLimit(limit);
        return ticketSettingsRepository.save(settings);
    }
}
