package com.example.demo.ticket;

import com.example.demo.producto.Producto;
import com.example.demo.producto.VersionProducto;
import com.example.demo.ticket.estado.EstadoTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
@RequestMapping(path = "/soporte")
public class TicketController {
    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    //GETS
   @GetMapping(path = "/tickets/{versionId}")
    //update Ticket in system
    public ResponseEntity<List<TicketTable>> getTickets(@PathVariable @RequestParam (required = false) Integer versionId) {
        List<TicketTable> tickets = ticketService.getTicketsByVersion(versionId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping(path = "/productos") //todos los productos, con sus versiones
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
    @PostMapping(path = "/ticket")
    //add new ticket to our system
    public ResponseEntity<TicketTable> createTicket(@RequestBody TicketTable ticketTable){
        VersionProducto versionProducto = new VersionProducto();
        Ticket ticket = new Ticket(ticketTable,versionProducto);
        ticket.abrirTicket();
        TicketTable ticketTableResultado = ticketService.createTicket(ticketTable);
        return new ResponseEntity<>(ticketTableResultado,HttpStatus.CREATED);
    }

    //PUTS y PATCHS
    @PutMapping(path = "/ticket/{ticketId}")
    //update Ticket in systemD
    public void updateTicket(
            @PathVariable("ticketId") Integer ticketId,
            @RequestParam(required = false) String cuit,
            @RequestParam(required = false) EstadoTicket Estado,
            @RequestParam(required = false) Integer severidad,
            @RequestParam(required = false) DateFormat fechaVencimiento,
            @RequestParam(required = false) DateFormat fechaInicial,
            @RequestParam(required = false) String descripcion) {
        ticketService.updateTicket(ticketId, cuit, Estado, severidad, fechaVencimiento, fechaInicial, descripcion);
    }

}