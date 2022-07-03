package com.example.demo.ticket;

import com.example.demo.cliente.Cliente;
import com.example.demo.empleado.Empleado;
import com.example.demo.producto.Producto;
import com.example.demo.producto.Version;
import com.example.demo.tarea.Tarea;
import com.example.demo.ticket.estado.Abierto;
import com.example.demo.ticket.estado.Cerrado;
import com.example.demo.ticket.estado.EstadoTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "psa_back_soporte")//www.kdhaksdhask.com/psa_back_soporte/...
public class TicketController {
    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    //GETS
   @GetMapping(path = "/tickets")
    //update Ticket in system
    public List<Ticket> getTickets(
            @PathVariable("productoId") @RequestParam(required = false) Integer productoId,
            @PathVariable("versionId") @RequestParam(required = false) Integer versionId) {
        return ticketService.getTickets(productoId, versionId);
    }

    @GetMapping(path = "/cantidadTickets/{id_producto}-{id_version}")
    public Integer getCantidadTickets(@PathVariable("id_producto") @RequestParam(required = false) Integer productoId,
                                      @PathVariable("id_version") @RequestParam(required = false) Integer versionId){

        return ticketService.getCantidadTickets(productoId, versionId);
    }

    @GetMapping(path = "/tickets/{id_producto}, {id_version}")
    public List<Integer> getCantidadTicketsByEstadoProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                             @PathVariable("id_version") Integer versionId) {
        //Integer cantAbiertos = ticketService.getCantidadTicketsByEstadoByProductoAndVersion(new Abierto(), productoId, versionId);
        //Integer cantCerrados = ticketService.getCantidadTicketsByEstadoByProductoAndVersion(new Cerrado(), productoId, versionId);

       //return List.of(cantAbiertos, cantCerrados);
        return List.of(1, 2);
    }

    @GetMapping //todos los productos, con sus versiones
    public List<Producto> getAllProductos(){

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
        Version versionB1 = new Version(
                10, //id
                "0.9.8", //numero version
                productoB, //producto
                "Casi listo para el release. Necesita testing en el bug encontrado por el usuario" //caracteristicas
        );

        Version versionB2 = new Version(
                145,
                "1.0.2",
                productoB,
                "MVP terminado. Inicio de nueva feature"
        );

        //List<Version> versionesB = new List<>(versionB1, versionB2);
        //productoB.setVersiones(List.of(versionB1, versionB2)); //agrego versiones al producto

        return List.of(productoA, productoB, productoC);
    }

    @GetMapping(path = "url_server_soporte/tickets/{id_producto}, {id_version}")
    public List<List<Ticket>> getTicketsByEstadoProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                                   @PathVariable("id_version") Integer versionId) {
       // List<Ticket> ticketsAbiertos = ticketService.getTicketsByEstadoByProductoAndVersion(new Abierto(), productoId, versionId);
       // List<Ticket> ticketsCerrados = ticketService.getTicketsByEstadoByProductoAndVersion(new Cerrado(), productoId, versionId);

       // return List.of(ticketsAbiertos, ticketsCerrados);
        return List.of(null, null);
    }

    //  GETTERS del SISTEMA EXTERNO
    public Cliente[] getClientes() {
        final String uri = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cliente[]> response = restTemplate.getForEntity(uri, Cliente[].class);
        return response.getBody();
    }

    //  GETTERS de RECURSOS
    @GetMapping(path = "/empleados")
    public Empleado[] getEmpleados() {
        final String uri = "https://squad5-recursos.herokuapp.com/api/empleados";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Empleado[]> response = restTemplate.getForEntity(uri, Empleado[].class);
        Empleado[] empleados = response.getBody();
        return empleados;
    }

    @GetMapping(path = "/empleado")
    public Empleado getEmpleadoById(@PathVariable("empleadoId") int empleadoId) {
        final String uri = "https://squad5-recursos.herokuapp.com/api/empleados/{empleadoId}";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Empleado> response = restTemplate.getForEntity(uri, Empleado.class);
        return response.getBody();
    }

    //  GETTERS de PROJECTOS
    @GetMapping(path = "/tickets/{id_tarea}") //ayuda de fer -- revisar
    public ArrayList<Tarea> getTicketByTarea(@PathVariable("tareaId") Integer tareaId) {
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + tareaId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tarea[]> response = restTemplate.getForEntity(uri, Tarea[].class);
        Tarea[] tareas = response.getBody();
        return new ArrayList<>(List.of(tareas));
    }

    // POSTS
    @PostMapping
    //add new ticket to our system
    public void createTicket(@RequestBody TicketTable ticketTable){
        Ticket ticket = new Ticket();
        ticket.abrirTicket();
        ticketService.createTicket(ticketTable);
    }

/*    @PostMapping
    //add new tarea to our system
    public void createTarea(@RequestBody Tarea tarea){
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + tarea.getId() + "/tareas";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Empleado[]> response = restTemplate.getForEntity(uri, Empleado[].class);
        Empleado[] empleados = response.getBody();
    }*/




    //PUTS y PATCHS
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