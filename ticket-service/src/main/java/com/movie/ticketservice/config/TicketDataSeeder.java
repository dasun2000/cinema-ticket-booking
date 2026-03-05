package com.movie.ticketservice.config;

import com.movie.ticketservice.entity.TicketOption;
import com.movie.ticketservice.entity.TicketSettings;
import com.movie.ticketservice.repository.TicketOptionRepository;
import com.movie.ticketservice.repository.TicketSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TicketDataSeeder implements CommandLineRunner {

    @Autowired
    private TicketOptionRepository ticketOptionRepository;

    @Autowired
    private TicketSettingsRepository ticketSettingsRepository;

    @Override
    public void run(String... args) throws Exception {
        if (ticketSettingsRepository.count() == 0) {
            ticketSettingsRepository.save(new TicketSettings(10));
        }

        if (ticketOptionRepository.count() == 0) {
            ticketOptionRepository.saveAll(Arrays.asList(
                    new TicketOption(1, "https://cdn-icons-png.flaticon.com/512/411/411833.png"),
                    new TicketOption(2, "https://cdn-icons-png.flaticon.com/512/869/869680.png"),
                    new TicketOption(3, "https://cdn-icons-png.flaticon.com/512/1054/1054215.png"),
                    new TicketOption(4, "https://cdn-icons-png.flaticon.com/512/869/869680.png")));
        }
    }
}
