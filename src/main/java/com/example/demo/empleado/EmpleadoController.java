package com.example.demo.empleado;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "empleados")
public class EmpleadoController {

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
}
