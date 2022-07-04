package com.example.demo.tarea;

import com.example.demo.empleado.Empleado;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
@RestController
@RequestMapping(path = {"/proyecto", "tareas"})
public class TareaController {

    @GetMapping(path = "/ticket_table/{id_tarea}") //ayuda de fer -- revisar
    public ArrayList<Tarea> getTicketByTarea(@PathVariable("tareaId") Integer tareaId) {
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + tareaId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Tarea[]> response = restTemplate.getForEntity(uri, Tarea[].class);
        Tarea[] tareas = response.getBody();
        return new ArrayList<>();
    }

    @PostMapping(path = "???")
    //add new tarea to our system
    public void createTarea(@RequestBody Tarea tarea){
        final String uri = "https://moduloproyectos.herokuapp.com/proyectos/" + tarea.getId() + "/tareas";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Empleado[]> response = restTemplate.getForEntity(uri, Empleado[].class);
        Empleado[] empleados = response.getBody();

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
    }
}
