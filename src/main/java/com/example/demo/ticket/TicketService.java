package com.example.demo.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
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


    //GETTERS
    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Transactional
    public List<Ticket> geTicketsByProductoAndVersion(Integer productoId, Integer versionId) {
        return ticketRepository.findAllById(List.of(productoId, versionId));
    }

    public Integer getCantidadTicketsByProductoAndVersion(Integer productoId, Integer versionId) {
        List<Ticket> tickets = ticketRepository.findAllById(List.of(productoId, versionId));

        return tickets.size();
    }

    //POSTS

    public void createTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findTicketById(ticket.getId());
        if (ticketOptional.isPresent()){
            throw new IllegalStateException("id tomado. Elija otro.");
        }

        ticketRepository.save(ticket);
    }


    //PUTS
    @Transactional
    //Transactional me permite no usar queries de bases de datos
    public void updateTicket(Integer ticketId, Integer responsableId, EstadoTicket estado, Integer severidad, DateFormat fechaVencimiento, Integer clienteId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketId + "does not exist"));

        if (responsableId != null && !Objects.equals(ticket.getResponsableId(), responsableId)) {
            ticket.setResponsableId(responsableId);
        }

        if (estado != null && !Objects.equals(ticket.getEstado(), estado)) {
            ticket.setEstado(estado);
        }

        if (severidad != null && !Objects.equals(ticket.getSeveridad(), severidad)) {
            ticket.setSeveridad(severidad);
        }

        if (fechaVencimiento != null && !Objects.equals(ticket.getFechaVencimiento(), fechaVencimiento)) {
            ticket.setFechaVencimiento(fechaVencimiento);
        }

        if (clienteId != null && !Objects.equals(ticket.getClienteId(), clienteId)) {
            ticket.setClienteId(clienteId);
        }

        //hace falta actualizar la base de datos?
    }


    //DELETES
    public void deleteTicket(Integer ticketId) {
      boolean exists = ticketRepository.existsById(ticketId);
      if (!exists) {
          throw new IllegalStateException("ticket with id " + ticketId + "does not exists");
      }

      ticketRepository.deleteById(ticketId);
    }
}
