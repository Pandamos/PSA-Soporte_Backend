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
    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping(path = "url_server_soporte/tickets/{id_tarea}")
    public List<Ticket> getTicketByTarea(@PathVariable("tareaId") Integer tareaId) {
        //nuestro código que busca todos los tickets asociados a una tarea y los devuelve
        return null;
    }

    @GetMapping(path = "url_server_soporte/cantidadTickets/{id_producto}, {id_version}")
    public Integer getCantidadTicketsByProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                          @PathVariable("id_version") Integer versionId){

            //buscar en la tabla correspondiente la cantidad de tickets asociados a esta versión de producto y producto
        return ticketService.getCantidadTicketsByProductoAndVersion(productoId, versionId);
    }

    @GetMapping(path = "url_server_soporte/tickets/{id_producto}, {id_version}")
    public List<Ticket> geTicketsByProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                 @PathVariable("id_version") Integer versionId){

        //buscar en la tabla correspondiente los tickets asociados a esta versión de producto y producto
        return ticketService.geTicketsByProductoAndVersion(productoId, versionId);
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