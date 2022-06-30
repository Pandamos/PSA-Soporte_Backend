package com.example.demo.ticket;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    public List<Ticket> getTickets() {
        return List.of(
                new Ticket(
                        1,
                        "Descripcion del primer ticket",
                        3
                )
        );
    }
}
