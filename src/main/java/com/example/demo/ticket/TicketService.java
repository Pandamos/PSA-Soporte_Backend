package com.example.demo.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getTickets() {
       // return List.of(
       //         new Ticket(
       //                 1,
       //                 "Descripcion del primer ticket",
       //                 3
       //         )
       // );

        //una vez que la base de datos esté conectada y funcionando, borramos el código anterior y descomentamos el siguiente
        return ticketRepository.findAll();
    }


    public void createTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findTicketById(ticket.getId());
        if (ticketOptional.isPresent()){
            throw new IllegalStateException("id tomado. Elija otro.");
        }

        ticketRepository.save(ticket);
    }


    @Transactional
    //Transactional me permite no usar queries de bases de datos
    public void updateTicket(Integer ticketId, String descripcion, Integer severidad){
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketId + "does not exist"));

        if (descripcion != null && descripcion.length() > 0 && !Objects.equals(ticket.getDescripcion(), descripcion)) {
            ticket.setDescripcion(descripcion);
        }

        if (severidad != null && !Objects.equals(ticket.getSeveridad(), severidad)) {
            ticket.setSeveridad(severidad);
        }

    }

    public void deleteTicket(Integer ticketId) {
      boolean exists = ticketRepository.existsById(ticketId);
      if (!exists) {
          throw new IllegalStateException("ticket with id " + ticketId + "does not exists");
      }

      ticketRepository.deleteById(ticketId);
    }
}
