package com.example.demo.ticket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TicketConfig {

    @Bean
    CommandLineRunner commandLineRunner(TicketRepository repository) {
        return args -> {
            Ticket ticket_1 = new Ticket(
                    1,
                    "Descripcion del primer ticket",
                    3
            );
            Ticket ticket_2 = new Ticket (
                    2,
                    "Descripcion del segundo ticket",
                    2
            );

            repository.saveAll(
                    List.of(ticket_1, ticket_2)
            );
        };
    }
}
