package com.example.demo.tarea;

import com.example.demo.empleado.Empleado;
import com.example.demo.ticket.*;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/proyectos")
public class TareaController {

    @GetMapping(path = "/ticket_table/{id_tarea}") //
    public Integer getTicketIdByTarea(@PathVariable("id_tarea") Integer tareaId) {
        final String uri = "https://moduloproyectos.herokuapp.com/tareas";
        RestTemplate restTemplate = new RestTemplate();
        Tarea[] tareas = restTemplate.getForEntity(uri, Tarea[].class).getBody();

        Tarea tarea = null;

        int i = 0;
        boolean salir = false;
        while (i < tareas.length && !salir) {
            if (tareas[i].getId().equals(tareaId)) {
                tarea = tareas[i];
                salir = true;
            }
            i++;
        }
        if (tarea == null) {
            return null;
        }

        return tarea.getIdTicket();
    }

    @GetMapping (path = "/tareas") //PROBADO
    public Tarea[] getTareas(@PathVariable @RequestParam (required = false) Integer ticketId) {
        //get todas las tareas -- PROBADO
        final String uri = "https://moduloproyectos.herokuapp.com/tareas";

        RestTemplate restTemplate = new RestTemplate();
        Tarea[] tareas = restTemplate.getForEntity(uri, Tarea[].class).getBody();

        if (ticketId == null) {
            return tareas;
        }

        //get tareas con ticketId -- PROBADO
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


    @PostMapping(path = "/{id_ticket}/tarea") //
    //add new tarea to our system
    public String createTarea(@PathVariable("id_ticket") Integer ticketId, @RequestBody Tarea tarea){
        final String uri = "https://moduloproyectos.herokuapp.com/" + tarea.getId() + "/tareas";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Tarea> headerRequestEntity = new HttpEntity<Tarea>(tarea, headers);

        String response = restTemplate.exchange(uri, HttpMethod.POST, headerRequestEntity, String.class).getBody();

        //linkeamos el ticket con la tarea
        final String uri_addTicket = "https://moduloproyectos.herokuapp.com/tareas/" + tarea.getId() + "/tickets/" + ticketId;

        restTemplate = new RestTemplate();
        restTemplate.exchange(uri_addTicket, HttpMethod.POST, null, void.class);

        return response;
    }



    @PutMapping (path = "/updateTarea/{id_proyecto}/{id_tarea}/{id_ticket}") //
    //link tarea to ticket
    public void updateTarea(@RequestBody Tarea tarea,
                            @PathVariable("id_proyecto") Integer idProyecto,
                            @PathVariable("id_tarea") Integer idTarea,
                            @PathVariable("id_ticket") Integer idTicket) {

       /* //mandamos la actualización de la tarea a los de proyectos
       final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + idProyecto + "/tareas/" + tarea.getId();
        // tarea.setIdTicket(idTicket);

        HttpHeaders headers = new HttpHeaders();
        // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        Map<String, Object> tarea_map = new HashMap<>();
        tarea_map.put("descripcion", tarea.getDescripcion());
        tarea_map.put("estado", tarea.getEstado());
        tarea_map.put("id", tarea.getId());
        //tarea_map.put("idProyecto",idProyecto);
        //tarea_map.put("idTicket", idTicket);
        tarea_map.put("nombre", tarea.getNombre());
        tarea_map.put("fechaCreacion", tarea.getFechaCreacion());

        JSONObject json = new JSONObject();
        json.putAll(tarea_map);

        HttpEntity<JSONObject> headerRequestEntity = new HttpEntity<JSONObject>(json, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.exchange(uri, HttpMethod.POST, headerRequestEntity, String.class).getBody();
*/

        //mandamos la actualización de la tarea a los de proyectos
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + idProyecto + "/tareas/" + idTarea;
        HttpHeaders headers = new HttpHeaders();
/*
        JSONObject tareaJsonObject = new JSONObject();

        tareaJsonObject.put("id", tarea.getId());
        tareaJsonObject.put("nombre", tarea.getNombre());
        tareaJsonObject.put("descripcion", tarea.getDescripcion());
        tareaJsonObject.put("estado", tarea.getEstado());
        tareaJsonObject.put("fechaCreacion", tarea.getFechaCreacion());
        tareaJsonObject.put("idTicket", tarea.getIdTicket());
        tareaJsonObject.put("idProyecto", tarea.getIdProyecto());
*/
        Gson gson = new Gson();
        String json_tarea = gson.toJson(tarea);
        HttpEntity<String> requestPut = new HttpEntity<String>(json_tarea, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, requestPut, String.class);


        //linkeamos el ticket con la tarea
        final String uri_addTicket = "https://moduloproyectos.herokuapp.com/tareas/" + idTarea + "/tickets/" + idTicket;

        restTemplate = new RestTemplate();
        restTemplate.exchange(uri_addTicket, HttpMethod.POST, null, void.class);

    }
}