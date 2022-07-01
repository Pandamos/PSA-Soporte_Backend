package com.example.demo.ticket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TicketConfig {

    @Bean
    CommandLineRunner commandLineRunner(TicketRepository repository) {
       /*return args -> {
            Ticket ticket_1 = new Ticket(
                    1,
                    titulo, "Descripcion del primer ticket",
                    3,
                    fechaVencimiento, idResponsable, idCliente);
            Ticket ticket_2 = new Ticket (
                    2,
                    titulo, "Descripcion del segundo ticket",
                    2,
                    fechaVencimiento, idResponsable, idCliente);

            repository.saveAll(
                    List.of(ticket_1, ticket_2)
            );
        }; */
        return null;
    }
}
