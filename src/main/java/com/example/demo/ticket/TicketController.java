package com.example.demo.ticket;

import com.example.demo.producto.Producto;
import com.example.demo.producto.VersionProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/soporte")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    public TicketController() {
        this.ticketService = ticketService;
    }

    //GETS
   @GetMapping(path = "/tickets")
    //devuelve Response Entity de lista de tickets
    //si ningun ticket matchea con el versionId pasado, se devuelve un response entity de una lista vacía
    //el status siempre es Ok
    public ResponseEntity<List<TicketTable>> getTickets(@PathVariable @RequestParam (required = false) Integer versionId) {
        List<TicketTable> tickets = ticketService.getTicketsByVersion(versionId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }
    //Devuelve un ticket en especifico segun el id de ticket que le llega.
    @GetMapping(path = "/ticket/{ticketId}")
    public TicketTable getOneTicket(@PathVariable Integer ticketId){
        TicketTable ticket = ticketService.getTicketById(ticketId);
        return ticket;
    }

    //Devuelve todos los tickets
    @GetMapping(path = "/allTickets")
    public Iterable<TicketTable> getAllTickets(){
        Iterable<TicketTable> tickets = ticketService.getAllTickets();
        return tickets;
    }

    @GetMapping(path = "/productos")
    //devuelve todos los productos, con sus versiones
    //no puede fallar
    public List<Producto> getProductos(){

        //PRODUCTOS
        Producto productoA = new Producto(
                14, //id
                "testing", //fase
                "Nombre producto A" //nombre
        );

        Producto productoB = new Producto(
                155,
                "deployed",
                "Nombre producto B"
        );

        Producto productoC = new Producto(
                86,
                "maintenance",
                "Nombre producto C"
        );

        //VERSIONES
        VersionProducto versionProductoA = new VersionProducto(
                3, //id
                "0.2.4", //numero version
                productoA.getId(), //producto
                "Pre-Alpha" //caracteristicas
        );
        productoA.setVersion(versionProductoA);

        VersionProducto versionProductoB1 = new VersionProducto(
                10, //id
                "0.9.8", //numero version
                productoB.getId(), //producto
                "Casi listo para el release. Necesita testing en el bug encontrado por el usuario" //caracteristicas
        );
        productoB.setVersion(versionProductoB1);

        VersionProducto versionProductoB2 = new VersionProducto(
                145,
                "1.0.2",
                productoB.getId(),
                "MVP terminado. Inicio de nueva feature"
        );
        productoB.setVersion(versionProductoB2);

        VersionProducto versionProductoC = new VersionProducto(
                514, //id
                "1.1.0.0", //numero version
                productoC.getId(), //producto
                "Comienzo de nueva feature" //caracteristicas
        );
        productoC.setVersion(versionProductoC);


        List<Producto> productos = new ArrayList<>();
        productos.add(productoA);
        productos.add(productoB);
        productos.add(productoC);

        return productos;
    }

    // POSTS
    @PostMapping(path = "/ticket") //funciona
    //crea un ticket nuevo al sistema
    //devuelve un response entity con el status CREATED y la nueva ticketTable
    public ResponseEntity<TicketTable> createTicket(@RequestBody TicketTable ticketTable, @RequestBody VersionProducto versionProducto){
        Ticket ticket = new Ticket(ticketTable, versionProducto);
        ticket.setVersion(versionProducto);
        TicketTable ticketTableResultado = ticketService.createTicket(ticketTable);
        return new ResponseEntity<>(ticketTableResultado,HttpStatus.CREATED);
    }

    //PUTS y PATCHS
    @PutMapping(path = "/ticket/{ticketId}") //funciona
    //update Ticket in systemD
    public ResponseEntity<TicketTable>  updateTicket(   @PathVariable("ticketId") Integer ticketId,
                                                        @RequestParam(required = false) String cuit,
                                                        @RequestParam(required = false) String estado,
                                                        @RequestParam(required = false) Integer severidad,
                                                        @RequestParam(required = false) LocalDate fechaVencimiento,
                                                        @RequestParam(required = false) LocalDate fechaInicial,
                                                        @RequestParam(required = false) String descripcion) {

        TicketTable ticketTableResponse = ticketService.updateTicket(ticketId, cuit, estado, severidad,
                                                                     fechaVencimiento, fechaInicial, descripcion);

        return new ResponseEntity<>(ticketTableResponse, HttpStatus.OK);
    }

}