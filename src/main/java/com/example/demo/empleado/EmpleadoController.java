package com.example.demo.empleado;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})
@RequestMapping(path = "/recursos")
public class EmpleadoController {

    @GetMapping(path = "/empleados")
    public Empleado[] getEmpleados() {
        final String uri = "https://squad5-recursos.herokuapp.com/api/empleados";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Empleado[]> response = restTemplate.getForEntity(uri, Empleado[].class);
        Empleado[] empleados = response.getBody();
        return empleados;
    }

    @GetMapping(path = "/empleadoById/{empleadoId}")
    public Empleado getEmpleadoById(@PathVariable("empleadoId") int empleadoId) {
        final String uri = "https://squad5-recursos.herokuapp.com/api/empleados/{empleadoId}";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Empleado> response = restTemplate.getForEntity(uri, Empleado.class);
        return response.getBody();
    }

}
