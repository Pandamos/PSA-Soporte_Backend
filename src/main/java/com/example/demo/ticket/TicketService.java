package com.example.demo.ticket;

import com.example.demo.cliente.Cliente;
import com.example.demo.empleado.Empleado;
import com.example.demo.ticket.estado.EstadoTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

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

    //de Lau
/*
    @Override
    public List<Ticket> getAllTickets(){
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }
    @Override
    public Optional<Ticket> getTicket(Integer id) {
        return ticketRepository.findById(id);
    }
    @Override
    public void addTicket(Ticket ticket){
        ticketRepository.save(ticket);
    }
    @Override
    public void updateTicket(Ticket ticket,Integer id){
        ticketRepository.save(ticket);
    }

    @Override
    public void delete(Integer id){
        ticketRepository.deleteById(id);
    }
*/

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    //GETTERS
    public List<Ticket> getTickets(Integer productoId, Integer versionId) {
        if (productoId != null && versionId != null) {
            return ticketRepository.findTicketsByProductIdAndVersionId(productoId, versionId);
        }
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByEstadoByProductoAndVersion(EstadoTicket estado, Integer productoId, Integer versionId) {
        return ticketRepository.findTicketsByEstadoByProductIdAndVersionId(estado.getestadoId(), productoId, versionId);
    }

    public Integer getCantidadTickets(Integer productoId, Integer versionId) {
        List<Ticket> tickets;
        if (productoId != null && versionId != null) {
            tickets = ticketRepository.findTicketsByProductIdAndVersionId(productoId, versionId);
        } else {
            tickets = ticketRepository.findAll();
        }
        return tickets.size();

    }

    public Integer getCantidadTicketsByEstadoByProductoAndVersion(EstadoTicket estado, Integer productoId, Integer versionId) {
        List<Ticket> tickets = ticketRepository.findTicketsByEstadoByProductIdAndVersionId(estado.getestadoId(), productoId, versionId);

        return tickets.size();
    }


    //POSTS

    public void createTicket(Ticket ticket) {
        Optional<Ticket> ticketOptional = ticketRepository.findTicketById(ticket.getId());
        if (ticketOptional.isPresent()) {
            throw new IllegalStateException("id tomado. Elija otro.");
        }

        ticketRepository.save(ticket);
    }


    //PUTS
    @Transactional
    //Transactional me permite no usar queries de bases de datos
    public void updateTicket(Integer ticketId, Integer responsableId, EstadoTicket estado, Integer severidad, DateFormat fechaVencimiento, Integer clienteId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IllegalStateException("ticket with id" + ticketId + "does not exist"));

        /*if (responsableId != null && !Objects.equals(ticket.getResponsableId(), responsableId)) {
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
        }*/

        ticketRepository.save(ticket);
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
