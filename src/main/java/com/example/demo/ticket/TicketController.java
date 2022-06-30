package com.example.demo.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/version1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @PostMapping
    //add new ticket to our system
    //no lo puedo probar todavía, no me deja
    public void createTicket(@RequestBody Ticket ticket){
        ticketService.createTicket(ticket);
    }

    @PutMapping
    //update resource in system
    public void updateTicket(
            @PathVariable("ticketId") Integer ticketId,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) Integer severidad) {
        ticketService.updateTicket(ticketId, descripcion, severidad);
    }



    @DeleteMapping(path = "{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Integer ticketId){
        ticketService.deleteTicket(ticketId);
    }
}
