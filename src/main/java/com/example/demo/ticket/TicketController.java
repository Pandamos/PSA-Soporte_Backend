package com.example.demo.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.text.DateFormat;
import java.util.List;

@RestController
@RequestMapping(path = "api/version1/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //GETS
   @GetMapping(path = "url_server_soporte/tickets?id_producto={id_producto}&id_version={id_version}")
    //update Ticket in system
    public List<Ticket> getTickets(
            @PathVariable("productoId") @RequestParam(required = false) Integer productoId,
            @PathVariable("versionId") @RequestParam(required = false) Integer versionId) {
        return ticketService.getTickets(productoId, versionId);
    }



            @GetMapping(path = "url_server_soporte/tickets/{id_tarea}")
    public List<Ticket> getTicketByTarea(@PathVariable("tareaId") Integer tareaId) {
        //nuestro código que busca todos los tickets asociados a una tarea y los devuelve
        return null;
    }

    @GetMapping(path = "url_server_soporte/cantidadTickets/{id_producto}, {id_version}")
    public Integer getCantidadTickets(@PathVariable("id_producto") @RequestParam(required = false) Integer productoId,
                                      @PathVariable("id_version") @RequestParam(required = false) Integer versionId){

        return ticketService.getCantidadTickets(productoId, versionId);
    }


    @GetMapping(path = "url_server_soporte/tickets/{id_producto}, {id_version}")
    public List<Integer> getCantidadTicketsByEstadoProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                             @PathVariable("id_version") Integer versionId) {
        Integer cantAbiertos = ticketService.getCantidadTicketsByEstadoByProductoAndVersion(new Abierto(), productoId, versionId);
        Integer cantCerrados = ticketService.getCantidadTicketsByEstadoByProductoAndVersion(new Cerrado(), productoId, versionId);

        return List.of(cantAbiertos, cantCerrados);
    }

    @GetMapping(path = "url_server_soporte/tickets/{id_producto}, {id_version}")
    public List<List<Ticket>> getTicketsByEstadoProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                                      @PathVariable("id_version") Integer versionId) {
        List<Ticket> ticketsAbiertos = ticketService.getTicketsByEstadoByProductoAndVersion(new Abierto(), productoId, versionId);
        List<Ticket> ticketsCerrados = ticketService.getTicketsByEstadoByProductoAndVersion(new Cerrado(), productoId, versionId);

        return List.of(ticketsAbiertos, ticketsCerrados);
    }

    // POSTS
    @PostMapping
    //add new ticket to our system
    public void createTicket(@RequestBody Ticket ticket){
        ticketService.createTicket(ticket);
    }


    //PUTS
    @PutMapping
    //update Ticket in system
    public void updateTicket(
            @PathVariable("ticketId") Integer ticketId,
            @RequestParam(required = false) Integer responsableId,
            @RequestParam(required = false) EstadoTicket Estado,
            @RequestParam(required = false) Integer severidad,
            @RequestParam(required = false) DateFormat fechaVencimiento,
            @RequestParam(required = false) Integer clienteId) {
        ticketService.updateTicket(ticketId, responsableId, Estado, severidad, fechaVencimiento, clienteId);
    }


    //DELETES
    @DeleteMapping(path = "{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Integer ticketId){
        ticketService.deleteTicket(ticketId);
    }
}