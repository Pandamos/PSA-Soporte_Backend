package com.example.demo.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/servicio_externo")
public class ClienteController {

    @GetMapping(path = "/clientes")
    public Cliente[] getClientes() {
        final String uri = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cliente[]> response = restTemplate.getForEntity(uri, Cliente[].class);
        return response.getBody();
    }


}
