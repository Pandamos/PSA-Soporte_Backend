package com.example.demo.tarea;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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


    @PostMapping(path = "/{id_ticket}/{id_proyecto}/tarea", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> createTarea(@PathVariable("id_ticket") Integer ticketId,
                                              @PathVariable("id_proyecto") Integer proyectoId,
                                              @RequestBody Tarea tarea) {
        tarea.setIdTicket(ticketId);

        String url = "https://moduloproyectos.herokuapp.com/proyectos/" + proyectoId + "/tareas";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(url, tarea, String.class);
    }

    @PostMapping (path = "/updateTarea/{id_proyecto}/{id_ticket}") //
    //link tarea to ticket
    public void updateTarea(@RequestBody Tarea tarea,
                            @PathVariable("id_proyecto") Integer idProyecto,
                            @PathVariable("id_ticket") Integer idTicket) {

       /* //mandamos la actualización de la tarea a los de proyectos
       final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + idProyecto + "/tareas/" + tarea.getId();
        // tarea.setIdTicket(idTicket);
	@@ -134,14 +134,12 @@ public String updateTarea(@RequestBody Tarea tarea,
        HttpEntity<JSONObject> headerRequestEntity = new HttpEntity<JSONObject>(json, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.exchange(uri, HttpMethod.POST, headerRequestEntity, String.class).getBody();
*/
        //linkeamos el ticket con la tarea
        final String uri_addTicket = "https://moduloproyectos.herokuapp.com/tareas/" + tarea.getId() + "/tickets/" + idTicket;


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(uri_addTicket, HttpMethod.POST, null, void.class);

    }




}