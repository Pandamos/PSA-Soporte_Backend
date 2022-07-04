package com.example.demo.tarea;

import com.example.demo.empleado.Empleado;
import com.example.demo.ticket.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Arrays;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/proyectos")
public class TareaController {

    @GetMapping(path = "/ticket_table/{id_tarea}")
    public TicketTable[] getTicketByTarea(@PathVariable("tareaId") Integer tareaId) {
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + tareaId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TicketTable[]> response = restTemplate.getForEntity(uri, TicketTable[].class);
        TicketTable[] tickets = response.getBody();
        return tickets;
    }

    @GetMapping (path = "/tareas")
    public Tarea[] getTareas(@RequestParam (required = false) Integer ticketId) {
        //get todas las tareas
        final String uri = "https://moduloproyectos.herokuapp.com/tareas";

        RestTemplate restTemplate = new RestTemplate();
        Tarea[] tareas = restTemplate.getForEntity(uri, Tarea[].class).getBody();

        if (ticketId == null) {
            return tareas;
        }

        //get tareas con ticketId
        Integer tareas_len = tareas.length;
        Tarea[] tareas_filtradas = new Tarea[tareas_len];
        int j = 0;
        for (int i = 0; i < tareas_len; i++) {
            if (tareas[i].getIdTicket().equals(ticketId)) {
                tareas_filtradas[j] = tareas[i];
                j++;
            }
        }

        return tareas_filtradas;
    }

    @GetMapping (path = "/proyectos") //PROBADO!
    public Proyecto[] getProyectos() {
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Proyecto[]> response = restTemplate.getForEntity(uri, Proyecto[].class);
        Proyecto[] proyectos = response.getBody();
        return proyectos;
    }

    @PostMapping(path = "/{id_ticket}/tareas")
    //add new tarea to our system
    public String createTarea(@PathParam("id_ticket") Integer ticketId, @RequestBody Tarea tarea){
        final String uri = "https://moduloproyectos.herokuapp.com/" + tarea.getId() + "/tareas";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        tarea.setIdTicket(ticketId);

        HttpEntity<Tarea> headerRequestEntity = new HttpEntity<Tarea>(tarea, headers);

        return restTemplate.exchange(uri, HttpMethod.POST, headerRequestEntity, String.class).getBody();
    }

    @PostMapping (path = "/updateTarea/{id_proyecto}")
    //link tarea to ticket
    public String updateTarea(@PathParam("id_proyecto") Integer idProyecto, @RequestBody Tarea tarea, @RequestBody TicketTable ticketTable) {
        //mandamos el ticket a los de proyectos
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + idProyecto + "/tareas" + tarea.getId();

        RestTemplate restTemplate = new RestTemplate();
        String respuesta = restTemplate.exchange(uri, HttpMethod.POST, null, String.class).getBody();

        //linkeamos el ticket con la tarea
        final String uri_addTicket = "https://moduloproyectos.herokuapp.com/tareas/" + tarea.getId() + "/tickets/" + ticketTable.getId();

        restTemplate = new RestTemplate();
        restTemplate.exchange(uri_addTicket, HttpMethod.POST, null, void.class);

        return respuesta;
    }

}