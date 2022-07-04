package com.example.demo.ticket;

import com.example.demo.cliente.Cliente;
import com.example.demo.empleado.Empleado;
import com.example.demo.producto.Producto;
import com.example.demo.producto.VersionProducto;
import com.example.demo.tarea.Tarea;
import com.example.demo.ticket.estado.EstadoTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.util.*;

@RestController
@RequestMapping(path = "https://primera-subida-psa.herokuapp.com")//www.kdhaksdhask.com/psa_back_soporte/...
public class TicketController {
    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    //GETS
   @GetMapping(path = "/ticket/{versionId}")
    //update Ticket in system
    public List<Ticket> getTickets(@PathVariable Integer versionId) {
        return ticketService.getTickets(versionId);
    }


    /*@GetMapping(path = "/ticket_table/{id_producto}, {id_version}")
    public List<Integer> getCantidadTicketsByEstadoProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                             @PathVariable("id_version") Integer versionId) {
        //Integer cantAbiertos = ticketService.getCantidadTicketsByEstadoByProductoAndVersion(new Abierto(), productoId, versionId);
        //Integer cantCerrados = ticketService.getCantidadTicketsByEstadoByProductoAndVersion(new Cerrado(), productoId, versionId);

       //return List.of(cantAbiertos, cantCerrados);
        return new ArrayList<>();
    }*/

    /*@GetMapping //todos los productos, con sus versiones
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
        VersionProducto versionProductoB1 = new VersionProducto(
                10, //id
                "0.9.8", //numero version
                productoB, //producto
                "Casi listo para el release. Necesita testing en el bug encontrado por el usuario" //caracteristicas
        );

        VersionProducto versionProductoB2 = new VersionProducto(
                145,
                "1.0.2",
                productoB,
                "MVP terminado. Inicio de nueva feature"
        );

        //List<Version> versionesB = new List<>(versionB1, versionB2);
        //productoB.setVersiones(List.of(versionB1, versionB2)); //agrego versiones al producto

       // return List.of(productoA, productoB, productoC);
        return new ArrayList<Producto>();
    }*/

    /*@GetMapping(path = "url_server_soporte/ticket_table/{id_producto}, {id_version}")
    public List<List<Ticket>> getTicketsByEstadoProductoAndVersion(@PathVariable("id_producto") Integer productoId,
                                                                   @PathVariable("id_version") Integer versionId) {
       // List<Ticket> ticketsAbiertos = ticketService.getTicketsByEstadoByProductoAndVersion(new Abierto(), productoId, versionId);
       // List<Ticket> ticketsCerrados = ticketService.getTicketsByEstadoByProductoAndVersion(new Cerrado(), productoId, versionId);

       // return List.of(ticketsAbiertos, ticketsCerrados);
        //return List.of(null, null);
        ArrayList<Ticket> tickets = new ArrayList<>();
        return new ArrayList<>();
    }*/

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
    /*@GetMapping(path = "/ticket_table/{id_tarea}") //ayuda de fer -- revisar
    public ArrayList<Tarea> getTicketByTarea(@PathVariable("tareaId") Integer tareaId) {
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + tareaId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tarea[]> response = restTemplate.getForEntity(uri, Tarea[].class);
        Tarea[] tareas = response.getBody();
       // return new ArrayList<>(List.of(tareas));
        return new ArrayList<>();
    }*/

    // POSTS
    @PostMapping
    //add new ticket to our system
    public void createTicket(@RequestBody TicketTable ticketTable){
        VersionProducto versionProducto = new VersionProducto();
        Ticket ticket = new Ticket(ticketTable,versionProducto);
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

        Tarea tarea = new Tarea();
        HttpHeaders headers = new HttpHeaders();
        JSONObject tareaJsonObject = new JSONObject();

        tareaJsonObject.put("id", tarea.getId());
        tareaJsonObject.put("nombre", tarea.getNombre());
        tareaJsonObject.put("descripcion", tarea.getDescripcion());
        tareaJsonObject.put("estado", tarea.getEstado());
        tareaJsonObject.put("fechaCreacion", tarea.getFechaCreacion());
        tareaJsonObject.put("idTicket", tarea.getIdTicket());
        tareaJsonObject.put("idProyecto", tarea.getIdProyecto());

        HttpEntity<String> requestPost = new HttpEntity<String>(tareaJsonObject.toString(), headers);

        restTemplate.postForObject("url_api_proyectos", requestPost, String.class);


    }*/




    //PUTS y PATCHS
    @PutMapping
    //update Ticket in system
    public void updateTicket(
            @PathVariable("ticketId") Integer ticketId,
            @RequestParam(required = false) String CUIT,
            @RequestParam(required = false) EstadoTicket Estado,
            @RequestParam(required = false) Integer severidad,
            @RequestParam(required = false) DateFormat fechaVencimiento,
            @RequestParam(required = false) DateFormat fechaInicial,
            @RequestParam(required = false) String descripcion) {
        ticketService.updateTicket(ticketId, CUIT, Estado, severidad, fechaVencimiento, fechaInicial, descripcion);
    }


    //DELETES
    @DeleteMapping(path = "{ticketId}")
    public void deleteTicket(@PathVariable("ticketId") Integer ticketId){
        ticketService.deleteTicket(ticketId);
    }
}