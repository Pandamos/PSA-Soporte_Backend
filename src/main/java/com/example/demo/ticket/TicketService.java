package com.example.demo.ticket;

import com.example.demo.producto.VersionProducto;
import com.example.demo.ticket.estado.EstadoTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    //GETTERS
    public List<TicketTable> getTickets(Integer versionId) {

        if(versionId == null){
            return (List<TicketTable>) ticketRepository.findAll();
        }

        List<TicketTable> tickets = ticketRepository.findByVersion(versionId);

        return tickets;

    }

    //POSTS

    public TicketTable createTicket(TicketTable ticket) {
        return ticketRepository.save(ticket);
    }


    //PUTS
    @Transactional
    public TicketTable updateTicket(Integer ticketId, String cuit, String estado, Integer severidad, String fechaVencimiento, String fechaInicial, String descripcion) {
        TicketTable ticketTable = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketId + "does not exist"));
        Ticket ticket = new Ticket(ticketTable);

        if (cuit != null && !Objects.equals(ticketTable.getCuit(), cuit)) {
            ticketTable.setCuit(cuit);
        }

        if (estado != null && Objects.equals("cerrado", estado)) {
            ticket.cerrarTicket();
        }

        if (estado != null && Objects.equals("abierto", estado)) {
            ticket.abrirTicket();
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

        return ticketRepository.save(ticketTable);
    }

    public TicketTable getTicketById(Integer ticketId) {
        TicketTable ticketTable = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketId + "does not exist"));
        return ticketTable;
    }

    public List<TicketTable> getAllTickets() {
        List<TicketTable> ticketTables = new ArrayList<>();
        ticketRepository.findAll().forEach(ticketTables::add);
        return ticketTables;
    }
}
