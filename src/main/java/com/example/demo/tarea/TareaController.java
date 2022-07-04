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

@CrossOrigin(origins = "", allowedHeaders = "", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping(path = "/proyecto")
public class TareaController {

    @GetMapping(path = "/ticket_table/{id_tarea}") //ayuda de fer -- revisar
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
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/tareas";

        RestTemplate restTemplate = new RestTemplate();
        Tarea[] tareas = restTemplate.getForEntity(uri, Tarea[].class).getBody();

        if (ticketId == null)
            return tareas;

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

    @PostMapping(path = "/tarea")
    //add new tarea to our system
    public String createTarea(@RequestBody Tarea tarea){
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/{" + tarea.getId() + "}/tareas";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        /*JSONObject tareaJsonObject = new JSONObject();

        tareaJsonObject.put("id", tarea.getId());
        tareaJsonObject.put("nombre", tarea.getNombre());
        tareaJsonObject.put("descripcion", tarea.getDescripcion());
        tareaJsonObject.put("estado", tarea.getEstado());
        tareaJsonObject.put("fechaCreacion", tarea.getFechaCreacion());
        tareaJsonObject.put("idTicket", tarea.getIdTicket());
        tareaJsonObject.put("idProyecto", tarea.getIdProyecto());
        */
        HttpEntity<Tarea> headerRequestEntity = new HttpEntity<Tarea>(tarea, headers);

        return restTemplate.exchange(uri, HttpMethod.POST, headerRequestEntity, String.class).getBody();
    }

    @PostMapping (path = "/{tareaId}")
    //link tarea to ticket
    public String updateTarea(@PathParam("tareaId") Integer tareaId, @RequestBody TicketTable ticketTable) {
        //mandamos el ticket a los de proyectos
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/tareas/{" + tareaId + "}/tickets/{" + ticketTable.getId() + "}";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        //pasar los dos ids como parámetros -- ya están en la url
        return restTemplate.exchange(uri, HttpMethod.POST, null, String.class).getBody();
    }

}