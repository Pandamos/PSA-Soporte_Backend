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
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    //GETTERS
    public List<TicketTable> getTicketsByVersion(Integer versionId) {

        if(versionId == null){
            return ticketRepository.findAll();
        }

        List<TicketTable> tickets = ticketRepository.findByVersion(versionId);

        return tickets;

    }

    /*public List<TicketTable> getTicketsByEstadoByProductoAndVersion(EstadoTicket estado, Integer productoId, Integer versionId) {
        return ticketRepository.findTicketsByEstadoByProductIdAndVersionId(estado.getestadoId(), productoId, versionId);
    }*/


    /*public Integer getCantidadTicketsByEstadoByProductoAndVersion(EstadoTicket estado, Integer productoId, Integer versionId) {
        List<TicketTable> tickets = ticketRepository.findTicketsByEstadoByProductIdAndVersionId(estado.getestadoId(), productoId, versionId);

        return tickets.size();
    }*/


    //POSTS

    public void createTicket(TicketTable ticket) {
        Optional<TicketTable> ticketOptional = ticketRepository.findById(ticket.getId());
        if (ticketOptional.isPresent()) {
            throw new IllegalStateException("id tomado. Elija otro.");
        }

        ticketRepository.save(ticket);
    }


    //PUTS
    @Transactional //---- ACTUALIZAAAAR
    //Transactional me permite no usar queries de bases de datos
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


    //DELETES
    public void deleteTicket(Integer ticketId) {
        boolean exists = ticketRepository.existsById(ticketId);
        if (!exists) {
            throw new IllegalStateException("ticket with id " + ticketId + "does not exists");
        }

        ticketRepository.deleteById(ticketId);
    }
}
