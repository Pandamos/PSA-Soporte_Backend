package com.example.demo.ticket;


import com.example.demo.ticket.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketServiceInterface {

    /*Iterable<Ticket> findAll();
    Page<Ticket> findAll(Pageable pageable);
    Optional<Ticket> findById(Integer id);
    Ticket save(Ticket ticket);
    void deleteById(Integer id);*/
    List<Ticket> getAllTickets();

    Optional<Ticket> getTicket(Integer id);

    void addTicket(Ticket ticket);

    void updateTicket(Ticket ticket, Integer id);

    void delete(Integer id);
}