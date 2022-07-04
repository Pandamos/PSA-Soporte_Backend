package com.example.demo.ticket;

import com.example.demo.producto.VersionProducto;
import com.example.demo.ticket.estado.EstadoTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    //GETTERS
    public List<TicketTable> getTicketsByVersion(Integer versionId) {

        if(versionId == null){
            return (List<TicketTable>) ticketRepository.findAll();
        }

        List<TicketTable> tickets = ticketRepository.findByVersion(versionId);

        return tickets;

    }


    //POSTS

    public void createTicket(TicketTable ticket) {
        Optional<TicketTable> ticketOptional = ticketRepository.findById(ticket.getId());
        if (ticketOptional.isPresent()) {
            throw new IllegalStateException("id tomado. Elija otro.");
        }

        ticketRepository.save(ticket);
    }


    //PUTS
    @Transactional
    public void updateTicket(Integer ticketId, String CUIT, EstadoTicket estado, Integer severidad, DateFormat fechaVencimiento,DateFormat fechaInicial, String descripcion) {
        VersionProducto versionProducto = new VersionProducto();
        TicketTable ticketTable = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketId + "does not exist"));
        Ticket ticket = new Ticket(ticketTable,versionProducto);

        if (CUIT != null && !Objects.equals(ticketTable.getCUIT(), CUIT)) {
            ticketTable.setCUIT(CUIT);
        }

        if (estado != null && !Objects.equals(ticketTable.getEstado(), estado.getestadoId())) {
            ticket.cambiarEstado(estado);
        }

        if (severidad != null && !Objects.equals(ticketTable.getSeveridad(), severidad)) {
            ticketTable.setSeveridad(severidad);
        }

        if (fechaVencimiento != null && !Objects.equals(ticketTable.getFechaDeFinalizacion(), fechaVencimiento)) {
            ticketTable.setFechaDeFinalizacion(fechaVencimiento);
        }

        if (descripcion != null && !Objects.equals(ticketTable.getDescripcion(), descripcion)) {
            ticketTable.setDescripcion(descripcion);
        }

        if (fechaInicial != null && !Objects.equals(ticketTable.getFechaDeCreacion(), fechaInicial)) {
            ticketTable.setFechaDeCreacion(fechaInicial);
        }

        ticketRepository.save(ticketTable);
    }

}
